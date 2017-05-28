package cn.advu.workflow.web.common.interceptor;

import cn.advu.workflow.common.cache.RedisClient;
import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.domain.database.SysPermission;
import cn.advu.workflow.web.common.component.SpringContextUtil;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.common.loginContext.LoginTools;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Administrator on 2017/2/17.
 */
public class UrlInterceptor extends HandlerInterceptorAdapter {

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

            RedisClient redisClient = (RedisClient) SpringContextUtil.getBean("redisClient");
            String str = redisClient.getStr("login_permissions_key_" + loginUser.getUserId().toString());
            if(str != null ) {
                JSONArray array = JSONArray.fromObject(str);
                List<SysPermission> list = JSONArray.toList(array, new SysPermission(), new JsonConfig());

                for(SysPermission sp : list ){
                    if(sp.getMenuUri() != null){
                        if(requestUri.equals(sp.getMenuUri())){
                            flag = true;
                        }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
