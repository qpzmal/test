package cn.advu.workflow.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by kai on 16/5/19.
 * httpClient工具类
 */
public class HttpPoolUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpPoolUtil.class);


    private static AtomicLong atomicLong = new AtomicLong(0);

    private HttpPoolUtil() {
    }

    static PoolingHttpClientConnectionManager poolConnManager;

    static int maxTotalPool = 100;

    static int maxConPerRoute = 50;

    static int socketTimeout = 5000; // 请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。

    static int connectTimeout = 1000; // 设置连接超时时间，单位毫秒。

    static int connectionRequestTimeout = 2000; // 设置从connect Manager获取Connection超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。

    static {


        org.apache.http.config.Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolConnManager.setMaxTotal(maxTotalPool);
        poolConnManager.setDefaultMaxPerRoute(maxConPerRoute);
        SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(socketTimeout).build();
        poolConnManager.setDefaultSocketConfig(socketConfig);


    }

    public static PoolingHttpClientConnectionManager getPoolConnManager() {

        return poolConnManager;
    }


    public static CloseableHttpClient getConnection() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolConnManager)
                .setConnectionManagerShared(true)
                .setDefaultRequestConfig(requestConfig)
                .build();
        if (poolConnManager != null && poolConnManager.getTotalStats() != null && atomicLong.getAndDecrement() % 1000 == 0) {
            LOGGER.info("now client pool " + poolConnManager.getTotalStats().toString());
        }
        return httpClient;
    }

    private static HttpUriRequest getRequestMethod(Map<String, String> paramMap, String url, String reqData, HttpMethod method) {

        List<BasicNameValuePair> params = new ArrayList();
        Set<Map.Entry<String, String>> entrySet = paramMap.entrySet();
        for (Map.Entry<String, String> e : entrySet) {
            String name = e.getKey();
            String value = e.getValue();
            BasicNameValuePair pair = new BasicNameValuePair(name, value);
            params.add(pair);
        }
        HttpUriRequest reqMethod = null;

        switch (method) {
            case POST:
                RequestBuilder requestBuilder = RequestBuilder.post().setUri(url)
                        .addParameters(params.toArray(new BasicNameValuePair[params.size()]));
                if (reqData != null) {
                    requestBuilder.setEntity(new StringEntity(reqData, "UTF-8"));
                }
                reqMethod = requestBuilder.build();
                break;
            case GET:
            default:
                reqMethod = RequestBuilder.get().setUri(url)
                        .addParameters(params.toArray(new BasicNameValuePair[params.size()])).build();

        }

        return reqMethod;

    }

    public static String sendHttpGet(String url) {

        return sendHttpGet(new HashMap<String, String>(), url, null);

    }

    public static String sendHttpGet(Map<String, String> paramMap, String url) {

        return sendHttpGet(paramMap, url, null);

    }

    /**
     * @param paramMap
     * @param url
     * @param headerMap http请求头参数，如不需要，请传null
     * @return
     */
    public static String sendHttpGet(Map<String, String> paramMap, String url, Map<String, String> headerMap) {

        CloseableHttpClient client = getConnection();
        HttpResponse getresponse = null;

        HttpUriRequest get = getRequestMethod(paramMap, url, null, HttpMethod.GET);
        if (headerMap != null) {
            for (Map.Entry entry : headerMap.entrySet()) {
                get.setHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("sendHttpGet begin...url:" + url);
            LOGGER.debug("sendHttpGet ........uri:" + get.getURI());
            LOGGER.debug("sendHttpGet ........header:" + get.getAllHeaders());
        }

        try {
            getresponse = client.execute(get);
            return parseResponse(getresponse, url);
        } catch (IOException e) {
            LOGGER.error("httpGet()...error,url:" + url, e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                ;
            }
        }

        return null;
    }

    /**
     * 解析结果
     *
     * @param response
     * @param url
     * @return
     * @throws IOException
     */
    private static String parseResponse(HttpResponse response, String url) throws IOException {
        HttpEntity entity = response.getEntity();
        String encoding = "utf-8";
        if (entity.getContentType() != null) {
            if (entity.getContentType().getValue().toUpperCase().contains("GB2312")) {
                encoding = "gb2312";
            } else if (entity.getContentType().getValue().toUpperCase().contains("GBK")) {
                encoding = "gbk";
            }
        }

        String responseVal = EntityUtils.toString(entity, encoding);
        if (response.getStatusLine() != null && response.getStatusLine().getStatusCode() == 200) {
            return responseVal;
        }

        return null;
    }


    public static String sendHttpPost(Map<String, String> paramMap, String url) {

        return sendHttpPost(paramMap, url, null);
    }

    public static String sendHttpPost(Map<String, String> paramMap, String url, String reqData) {

        CloseableHttpClient client = getConnection();

        HttpUriRequest post = getRequestMethod(paramMap, url, reqData, HttpMethod.POST);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("sendHttpPost begin...url:" + url);
        }

        try {
            HttpResponse pstres = client.execute(post);
            return parseResponse(pstres, url);
        } catch (IOException e) {
            LOGGER.error("httpPost()...error,url:" + url, e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                ;
            }
        }

        return null;
    }


    enum HttpMethod {
        GET, POST
    }


    public static void main(String[] args) {

        System.out.println(sendHttpGet(new HashMap<String, String>(), "http://zlog.ikanshu.cn/log.js?cnid=1378&uid=2004052&cnid=1378&imsi=460014120501303&imei=863034024107372&cnsubid=&umeng=FreeShu_dianx2&version=2.2.0&oscode=17&model=Lenovo-A708t&other=a&mac=863034024107372&platform=android&appname=cxb&log=%7B%22pfp%22%3A%221-2%22%2C%22pft%22%3A5021%2C%22did%22%3A%22%22%2C%22msg%22%3A%22%22%7D&rdm=9909", null));
    }
}
