package cn.advu.workflow.web.common.interceptor;

import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.exception.AuthException;
import cn.advu.workflow.web.common.loginContext.LoginTools;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/2/17.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);


//    @Autowired
//    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.debug("AuthInterceptor--preHandle start...............");
        Boolean flag = false;

        String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);

        // TODO 临时代码，loginInterceptor会校验此处
        if (StringUtils.isBlank(loginCookie)) {
            LOGGER.warn("loginCookie is null.");
            throw new AuthException("cookie获取失败。");
        }

        try {
            String requestUri = request.getRequestURI();
            LoginUser loginUser = LoginTools.parseLoginUser(loginCookie);

//            LoginAccount account = userService.getAccount(loginUser);
//            List<SysPermission> permissions = account.getPermissions();
//            for(SysPermission sp : permissions ){
//                if(sp.getMenuUri() != null){
//                    if(requestUri.equals(sp.getMenuUri())){
//                        flag = true;
//                    }
//                }
//            }
            flag = true;

        } catch (Exception e) {
            flag = false;
            LOGGER.warn("AuthInterceptor has error, ", e);
            e.printStackTrace();
        }
        if (!flag) {
            throw new AuthException("鉴权失败。");
        }
        return flag;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LOGGER.debug("AuthInterceptor--afterCompletion start...............");
    }
}
