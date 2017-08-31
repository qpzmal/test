package cn.advu.workflow.web.example.mail.sms163;


import net.sf.json.JSONArray;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by weiqz on 2017/7/18.
 *
 * 参考：http://dev.netease.im/docs/product/%E7%9F%AD%E4%BF%A1/%E7%9F%AD%E4%BF%A1%E6%8E%A5%E5%85%A5%E7%A4%BA%E4%BE%8B
 * http://netease.im/price
 */
public class SendNeteaseTemplate {

    //发送验证码的请求路径URL
    private static final String SERVER_URL="https://api.netease.im/sms/sendtemplate.action";

    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String APP_KEY="375af8e28c4774006170a3f84ef46505";

    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="f982aec90368";

    //随机数
    private static final String NONCE="123456";

    //短信模板ID
    private static final String TEMPLATEID="3060826";

    //手机号
    private static final String MOBILE="['13240427004']";

    public static void send(String mobile, String params) throws Exception{
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);

//        /*
//         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
//         */
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
        nvps.add(new BasicNameValuePair("mobiles", mobile));
        nvps.add(new BasicNameValuePair("params", JSONArray.fromObject(params).toString()));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
    }

    public static void main(String[] args) throws Exception {
        List<String> mobileList = new ArrayList<>();
        List<String> paramsList = new ArrayList<>();
        mobileList.add("13240427004");
        paramsList.add("abc");
        paramsList.add("【xxxxx】");
        SendNeteaseTemplate.send(JSONArray.fromObject(mobileList.toArray()).toString(), JSONArray.fromObject(paramsList.toArray()).toString());
    }
}
