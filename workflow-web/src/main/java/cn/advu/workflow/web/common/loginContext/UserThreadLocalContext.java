package cn.advu.workflow.web.common.loginContext;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by kai
 * 客户端标准参数容器
 * 代码中在当前线程用get
 */
public class UserThreadLocalContext extends
        BaseThreadLocalContext<UserThreadLocalContext> {

    private final static Logger LOGGER = Logger.getLogger(UserThreadLocalContext.class);

    private LoginUser user = new LoginUser();

    public static LoginUser getCurrentUser() {
        final UserThreadLocalContext loginContext = (UserThreadLocalContext) get();
        if (loginContext == null) {
            return null;
        }
        LoginUser user = loginContext.getUser();
        return user;
    }

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        if ( user != null ) {
            try {
                BeanUtils.copyProperties(this.user, user);
            } catch (IllegalAccessException e) {
                LOGGER.error("setParam() ERROR" , e);
            } catch (InvocationTargetException e) {
                LOGGER.error("setParam() ERROR", e);
            }

        }

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("setParam(),Thread:" + Thread.currentThread().getName() + ",user:" + user);
        }
    }

    static public void addCurrentUser(LoginUser loginUser) {
        UserThreadLocalContext context = (UserThreadLocalContext) get();
        if (context == null) {
            set(new UserThreadLocalContext());
            context = (UserThreadLocalContext) get();
        }
        context.setUser(loginUser);
    }
}