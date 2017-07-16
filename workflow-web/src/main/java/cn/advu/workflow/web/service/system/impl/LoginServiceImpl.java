package cn.advu.workflow.web.service.system.impl;

import cn.advu.workflow.common.constant.GlobalConstant;
import cn.advu.workflow.common.utils.md5.StrMD5;
import cn.advu.workflow.dao.fcf_vu.SysUserMapper;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.exception.LoginException;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.service.system.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {
    private static Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);
//
//    @Autowired
//    private UserMapper adminUserMapper;

    @Autowired
    private SysUserMapper sysUserMapper;



    @Override
    public void validLoginUser(LoginUser loginUser) throws LoginException {

        if (loginUser.getLoginTime() == null) {
            LOGGER.warn("登录时间验证错误，loginUser.logintime is null.");
            throw new LoginException("登录时间验证错误");
        }

        //校验用户名和密码、可以设置缓存
        SysUser dbUser = sysUserMapper.queryUserByNameAndId(loginUser.getUserName(), loginUser.getUserId());

        if (dbUser == null) {
            LOGGER.warn("登录时间验证错误，dbUser is null.");
            throw new LoginException("用户名和id验证错误");
        }
        if (dbUser.getLastLoginTime() == null) {
            LOGGER.warn("登录时间验证错误，dbUser.lastLoginTime is null.");
            throw new LoginException("登录时间验证错误");
        }
        if (loginUser.getLoginTime().longValue() != dbUser.getLastLoginTime().longValue()) {
            LOGGER.warn("登录时间验证错误，dbUser.lastLoginTime != loginUser.logintime.");
            throw new LoginException("登录时间验证错误");
        }

        //超过30天
        if (loginUser.getLoginTime().longValue() + GlobalConstant.CacheExpire.MONTH < System.currentTimeMillis()) {
            LOGGER.warn("登录时间验证错误，cookie timeout.");
            throw new LoginException("登录时间验证错误");
        }
        loginUser.setRealName(dbUser.getUserName());
    }

    @Override
    public LoginUser login(String uname, String inputPasswd, String vcode, HttpServletRequest request) throws LoginException {

        LoginUser loginUser = new LoginUser();
        try {
            //校验验证码是否正确
            //LoginTools.validVCode(vcode, request);


            //校验用户名和密码是否正确
            //更新数据库的最后登录时间
            //返回登录用户
            //校验不通过或者错误抛出登录异常

            String password = StrMD5.getInstance().encrypt(inputPasswd, WebConstants.MD5_SALT);

            SysUser user = sysUserMapper.queryUserByNameAndPassword(uname, password);

            if (user == null) {
                LOGGER.warn("用户名或密码错误，name:{}", uname);
                throw new LoginException("用户名或密码错误");
            }

            // 更新用户最后登录时间
            SysUser updateUser = new SysUser();
            updateUser.setId(user.getId());
            Long loginTime = System.currentTimeMillis();
            updateUser.setLastLoginTime(loginTime);
            sysUserMapper.updateByPrimaryKeySelective(updateUser);

            // 设置用户信息
            loginUser.setRealName(user.getUserName());
            loginUser.setUserName(uname);
            loginUser.setUserId(user.getId().toString());
            loginUser.setLoginTime(loginTime);
        } catch (LoginException e) {
            LOGGER.error("", e);
        }
        return loginUser;
    }

}
