package cn.advu.workflow.web.common.interceptor;

import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.loginContext.LoginTools;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/2/17.
 */
public class UrlInterceptor extends HandlerInterceptorAdapter {


//    @Autowired
//    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Boolean flag = false;

        try {
            String requestUri = request.getRequestURI();
            String loginCookie = RequestUtil.getCookieValue(request, Constants.Login.LOGIN_COOKIE_KEY);

            if (StringUtils.isBlank(loginCookie)) {
                return flag;
            }

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
            e.printStackTrace();
        }
        return flag;
    }
}
