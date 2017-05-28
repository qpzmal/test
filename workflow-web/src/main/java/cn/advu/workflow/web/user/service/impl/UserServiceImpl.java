package cn.advu.workflow.web.user.service.impl;


import cn.advu.workflow.common.constant.Constants;
import cn.advu.workflow.common.utils.StrMD5;
import cn.advu.workflow.dao.database.SysPermissionMapper;
import cn.advu.workflow.dao.database.UserMapper;
import cn.advu.workflow.domain.database.SysPermission;
import cn.advu.workflow.domain.database.User;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.common.exception.LoginException;
import cn.advu.workflow.web.common.loginContext.LoginAccount;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by kai
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper adminUserMapper;

    @Autowired
    private SysPermissionMapper permissionMapper;


    @Override
    public void validLoginUser(LoginUser loginUser) throws LoginException {

        //校验用户名和密码、可以设置缓存
        User adminUser = adminUserMapper.queryAdminUserByUserNameAndUserId(
                loginUser.getUserName(), loginUser.getUserId());

        if (adminUser == null) {
            throw new LoginException("用户名和id验证错误");
        }
        if (loginUser.getLoginTime() == null || adminUser.getLastLoginTime() == null) {
            throw new LoginException("登录时间验证错误");
        }
        if (loginUser.getLoginTime().longValue() != adminUser.getLastLoginTime().longValue()) {
            throw new LoginException("登录时间验证错误");
        }
        //超过30天
        if (loginUser.getLoginTime().longValue() + Constants.Login.COOKIE_EXPIRY_30_DAYS * 1000 < System.currentTimeMillis()) {
            throw new LoginException("登录时间验证错误");
        }
        loginUser.setRealName(adminUser.getRealname());
    }

    @Override
    public LoginUser login(String uname, String passwd, String vcode, HttpServletRequest request) throws LoginException {

        LoginUser loginUser = new LoginUser();
        try {
            //校验验证码是否正确
            //LoginTools.validVCode(vcode, request);


            //校验用户名和密码是否正确
            //更新数据库的最后登录时间
            //返回登录用户
            //校验不通过或者错误抛出登录异常

            String password = StrMD5.getInstance().encrypt(passwd, WebConstants.MD5_SALT);

            User adminUser = adminUserMapper.queryAdminUserByUserNameAndPassword(uname, password);

            if (adminUser == null) {
                throw new LoginException("用户名密码错误");
            }

            Long loginTime = System.currentTimeMillis();

            adminUser.setLastLoginTime(loginTime);

            adminUserMapper.updateAdminUser(adminUser);



            loginUser.setRealName(adminUser.getRealname());
            loginUser.setUserName(uname);
            loginUser.setUserId(adminUser.getId());
            loginUser.setLoginTime(loginTime);


        } catch (LoginException e) {
            logger.error(e);
        }
        return loginUser;
    }


    @Override
    public LoginAccount getAccount(LoginUser loginUser) {
        List<SysPermission> permissions = permissionMapper.getByUserId(loginUser.getUserId().intValue());
        LoginAccount account = new LoginAccount();
        account.setUser(loginUser);
        account.setPermissionUrls(permissions);
        return account;
    }
}
