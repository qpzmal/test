package cn.advu.workflow.web.common.loginContext;

import cn.advu.workflow.common.cache.RedisClient;
import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.common.utils.DesUtils;
import cn.advu.workflow.web.common.component.SpringContextUtil;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.exception.LoginException;
import cn.advu.workflow.web.common.filter.AccessFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kai on 15/8/5.
 */
public class LoginTools {

    private static Logger logger = Logger.getLogger(LoginTools.class);

    public static LoginUser parseLoginUser(String loginCookieStr) throws LoginException {

        try {
            DesUtils desUtils = new DesUtils();
            String loginStr = desUtils.decrypt(loginCookieStr);

            String[] strArr = loginStr.split(Constants.Login.LOGIN_COOKIE_SPLIT);

            LoginUser user = new LoginUser();

            user.setUserId(Long.parseLong(strArr[0]));

            user.setUserName(strArr[1]);

            user.setLoginTime(Long.parseLong(strArr[2]));

            return user;

        } catch (Exception ex) {

            logger.error("解析cookie失败:" + ex.getMessage() + ",cookieStr:" + loginCookieStr);
            throw new LoginException("解析cookie失败:" + ex.getMessage(), ex);
        }
    }

    public static String toCookieStr(LoginUser loginUser) {

        try {
            DesUtils desUtils = new DesUtils();

            StringBuffer sb = new StringBuffer();
            sb.append(loginUser.getUserId());
            sb.append(Constants.Login.LOGIN_COOKIE_SPLIT).append(loginUser.getUserName());
            sb.append(Constants.Login.LOGIN_COOKIE_SPLIT).append(loginUser.getLoginTime());

            return desUtils.encrypt(sb.toString());


        } catch (Exception ex) {

            logger.error("加密用户信息失败:" + ex.getMessage() + "," + loginUser);
            throw new LoginException("加密用户信息失败:" + ex.getMessage(), ex);
        }

    }

    public static void validVCode(String vcode, HttpServletRequest request) {
        String ssid = RequestUtil.getCookieValue(request, AccessFilter.SSID_KEY);

        RedisClient redisClient = (RedisClient) SpringContextUtil.getBean("redisClient");

        String token = redisClient.getStr("login_session_key_"+ ssid);
        if (StringUtils.isEmpty(token) || !vcode.equalsIgnoreCase(token)) {
            logger.info("当前的SessionID="+ssid+",vcode:" + vcode + ",token:" + token);
            throw new LoginException("验证码填写错误");
        }
    }

    public static void setVCode(String vcode, HttpServletRequest request) {


        String ssid = RequestUtil.getCookieValue(request, AccessFilter.SSID_KEY);

        RedisClient redisClient = (RedisClient) SpringContextUtil.getBean("redisClient");

        redisClient.setStrEx("login_session_key_"+ ssid, vcode, 60 * 60);

        logger.info("当前的会话ID=" + ssid + ",验证码=" + vcode);
    }

}
