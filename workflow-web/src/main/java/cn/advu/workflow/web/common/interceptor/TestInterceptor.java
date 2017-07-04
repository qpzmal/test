package cn.advu.workflow.web.common.interceptor;

import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import com.alibaba.dubbo.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/2/17.
 */
public class TestInterceptor extends HandlerInterceptorAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(TestInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // TODO 测试代码 START
        String loginName = request.getParameter("login_name");
        LoginUser loginUser = UserThreadLocalContext.getCurrentUser();
        if (StringUtils.isEmpty(loginName)) {
            LOGGER.debug("loginName is empty.");
            if (loginUser == null) {
                LOGGER.debug("loginUser is null.");
                loginName = "u1";
            } else {
                LOGGER.debug("loginUser is not null, loginUser.name:{}", loginUser.getUserName());
                loginName = loginUser.getUserName();
            }

        } else {
            LOGGER.debug("loginName is not empty, loginName:{}", loginName);
            if (loginUser == null) {
                LOGGER.debug("loginUser is null.");

            } else {
                LOGGER.debug("loginUser is not null, loginUser.name:{}", loginUser.getUserName());

            }

        }


        LOGGER.debug("loginName:{}", loginName);

        loginUser = new LoginUser();
        switch (loginName) {
            case "u1": // 媒介
                loginUser.setUserId("1");
                loginUser.setUserName("u1");
                break;
            case "u2": // 销售普通
                loginUser.setUserId("2");
                loginUser.setUserName("u2");
                break;
            case "u3": // 销售主管
                loginUser.setUserId("3");
                loginUser.setUserName("u3");
                break;
            case "u4": // 销售总经理
                loginUser.setUserId("4");
                loginUser.setUserName("u4");
                break;
            case "u5": // 财务
                loginUser.setUserId("5");
                loginUser.setUserName("u5");
                break;
            case "u6": // 法务
                loginUser.setUserId("6");
                loginUser.setUserName("u6");
                break;
            case "u7": // 4A
                loginUser.setUserId("7");
                loginUser.setUserName("u7");
                break;
            case "u8": // hr(管理部门、通讯录)
                loginUser.setUserId("8");
                loginUser.setUserName("u8");
                break;
            default:
                loginUser.setUserId("1");
                loginUser.setUserName("u1");
                break;
        }


//        loginUser.setUserId("4");
//        loginUser.setUserName("u4");
        UserThreadLocalContext.addCurrentUser(loginUser);

        // TODO 测试代码 END
        return true;
    }
}
