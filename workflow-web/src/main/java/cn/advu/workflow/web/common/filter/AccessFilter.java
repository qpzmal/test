package cn.advu.workflow.web.common.filter;


import cn.advu.workflow.common.utils.UUIDUtil;
import cn.advu.workflow.web.common.RequestUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by kai
 * 访问filter，纪录所有访问日志
 * 校验cookie里是否有万维客户端标识，如果没有会设置一个有效期365*10天的cookie
 * 校验cookie里是否有当前会话标识，如果没有会设置一个会话级别的cookie
 */
public class AccessFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(AccessFilter.class);

    /**
     * 万维agent cookie key
     * 有效期365*10天
     */
    public static final String IWANVI_AGENT_KEY = "wwa";

    /**
     * 会话级cookie key
     * 当前会话有效，浏览器关闭后消息
     */
    public static final String SSID_KEY = "ssid";

    /**
     * 不需要记录访问日志的url
     * 类似：/static/这种
     */
    private List<String> skipURL = Collections.emptyList();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String skipurls = filterConfig.getInitParameter("skipurl");

        if (StringUtils.isNotBlank(skipurls)) {

            String[] arrs = skipurls.split(",");

            skipURL = new LinkedList<String>();
            for (String str : arrs) {
                if (StringUtils.isNotBlank(str)) {
                    skipURL.add(str);
                }
            }
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String uri = httpServletRequest.getRequestURI();

        if (isSkipURI(uri)) {  //如果配置了跳过地址，匹配上则直接跳走
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }


        Long beginTime = System.currentTimeMillis();

        String wwa = RequestUtil.getCookieValue(httpServletRequest, IWANVI_AGENT_KEY);

        if (StringUtils.isBlank(wwa)) {
            wwa = UUIDUtil.generate24UUID();
            RequestUtil.addCookie(httpServletResponse, IWANVI_AGENT_KEY, wwa, 60*60*24*365*10);
        }

        String ssid = RequestUtil.getCookieValue(httpServletRequest, SSID_KEY);
        if (StringUtils.isBlank(ssid)) {
            ssid = UUIDUtil.generate24UUID();
            RequestUtil.addCookie(httpServletResponse, SSID_KEY, wwa, null);
        }


        String ua = RequestUtil.getStringHeaderDef(httpServletRequest, "User-Agent", "");

        String referer = RequestUtil.getStringHeaderDef(httpServletRequest, "referer", "");

        String ip = RequestUtil.getIpAddr(httpServletRequest);

        String pin = RequestUtil.getCookieValue(httpServletRequest, "pin", "");

        String url = httpServletRequest.getRequestURL().toString();

        String methodType = httpServletRequest.getMethod();

        //校验是否带有X-Requested-With头部，标准jquery的ajax会带上此头部（仅仅是约定，有可能不对）
        String reqType = RequestUtil.getStringHeaderDef(httpServletRequest, "X-Requested-With", "") == "" ? "form" : "ajax";

        Map<String, String> paramMap = getParam(httpServletRequest);


        Date accessTime = new Date(beginTime);

        //继续执行
        filterChain.doFilter(httpServletRequest, httpServletResponse);

        Long endTime = System.currentTimeMillis();

        Integer procTime = Integer.parseInt(Long.toString(endTime - beginTime));

        AccessLog accessLog = new AccessLog();
        accessLog.setAccessDate(accessTime);
        accessLog.setIp(ip);
        accessLog.setPin(pin);
        accessLog.setMethodType(methodType);
        accessLog.setParamMap(paramMap);
        accessLog.setProcTime(procTime);
        accessLog.setReferer(referer);
        accessLog.setUa(ua);
        accessLog.setUri(uri);
        accessLog.setUrl(url);
        accessLog.setReqType(reqType);
        accessLog.setSsid(ssid);
        accessLog.setWwa(wwa);

        //日志写入文件
        accessLog.writeToFile();

    }


    private Map<String, String> getParam(HttpServletRequest request) {

        Enumeration<String> enumeration = request.getParameterNames();

        Map<String, String> paramMap = new HashMap<String, String>();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            paramMap.put(name, request.getParameter(name));
        }

        return paramMap;
    }


    /**
     * 是否跳过该url
     *
     * @param uri
     * @return
     */
    private boolean isSkipURI(String uri) {

        for (String skip : skipURL) {

            if (uri.startsWith(skip)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void destroy() {

        LOGGER.info("destroy()...");

    }
}
