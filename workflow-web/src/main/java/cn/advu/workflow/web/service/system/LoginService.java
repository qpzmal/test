package cn.advu.workflow.web.service.system;

import cn.advu.workflow.web.common.exception.LoginException;
import cn.advu.workflow.web.common.loginContext.LoginAccount;
import cn.advu.workflow.web.common.loginContext.LoginUser;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by weiqz on 2017/6/24.
 */
public interface LoginService {

    /**
     * 校验登录用户是否匹配
     * 校验登录用户登录时间
     * @param loginUser
     * @throws LoginException
     */
    void validLoginUser(LoginUser loginUser) throws LoginException;


    /**
     * 登录，登录成功登录用户
     * @param uname
     * @param passwd
     * @param vcode
     * @param request
     * @return
     * @throws LoginException
     */
    LoginUser login(String uname, String passwd, String vcode, HttpServletRequest request) throws LoginException;

    /**
     * 获取用户资源信息
     * @param account
     * @return
     * @throws LoginException
     */
    void queryUserFunction(LoginAccount account) throws LoginException;

}
