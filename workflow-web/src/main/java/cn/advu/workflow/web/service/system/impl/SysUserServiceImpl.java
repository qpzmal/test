package cn.advu.workflow.web.service.system.impl;

import cn.advu.workflow.common.utils.md5.StrMD5;
import cn.advu.workflow.domain.enums.LogTypeEnum;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.facade.workflow.ActivitiFacade;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.manager.UserMananger;
import cn.advu.workflow.web.service.system.SysUserService;
import cn.advu.workflow.web.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService{
    private static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    SysUserRepo sysUserRepo;

    @Autowired
    ActivitiFacade activitiFacade;

    @Autowired
    UserMananger userMananger;

    @Autowired
    BizLogManager bizLogManager;

    @Override
    @Transactional
    public ResultJson<Integer> add(SysUser user) {

        // validator
        if (userMananger.isNameDuplicated(user.getId(), user.getLoginName())) {
            throw new ServiceException(MessageConstants.USER_NAME_IS_DUPLICATED);
        }

        // add
        user.setPassword(StrMD5.getInstance().encrypt(user.getPassword(), WebConstants.MD5_SALT));
        int result = sysUserRepo.addSelective(user);
        if(result == 0){
            throw new ServiceException("创建用户失败！");
        }

//        activitiFacade.createUser(user);

        // log
        bizLogManager.addBizLog(sysUserRepo.findOne(user.getId()), "用户管理/添加用户", Integer.valueOf(LogTypeEnum.ADD.getValue()));

        ResultJson<Integer> rj = new ResultJson<>();
        rj.setData(user.getId());
        return rj;
    }


    @Override
    @Transactional
    public ResultJson<Void> update(SysUser user) {

        Integer id = user.getId();
        AssertUtil.assertNotNull(id);

        // log
        bizLogManager.addBizLog(sysUserRepo.findOne(id), "用户管理/更新用户", Integer.valueOf(LogTypeEnum.UPDATE.getValue()));

        // validator
        if (userMananger.isNameDuplicated(user.getId(), user.getLoginName())) {
            throw new ServiceException(MessageConstants.USER_NAME_IS_DUPLICATED);
        }

        SysUser oldSysUser = sysUserRepo.findOne(id);
        String oldPassword = oldSysUser.getPassword();
        String currentPassword = user.getPassword();
        if (!oldPassword.equals(currentPassword)) {
            //密码加密
            user.setPassword(StrMD5.getInstance().encrypt(user.getPassword(), WebConstants.MD5_SALT));
        }

        int result = sysUserRepo.updateSelective(user);
        if(result == 0){
            throw new ServiceException("更新用户失败！");
        }


        return new ResultJson<>();
    }

    @Override
    public ResultJson<List<SysUser>> findAll() {

        List<SysUser> sysUserList = sysUserRepo.findAll();

        ResultJson<List<SysUser>> rj = new ResultJson();
        rj.setData(sysUserList);
        rj.setCode(WebConstants.OPERATION_SUCCESS);

        return rj;
    }

    @Override
    public ResultJson<SysUser> findByUserId(Integer userId) {
        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        SysUser sysUser = sysUserRepo.findOne(userId);
        resultJson.setData(sysUser);
        return resultJson;
    }

    @Override
    public ResultJson<Integer> remove(Integer userId) {
        // log
        bizLogManager.addBizLog(sysUserRepo.findOne(userId), "用户管理/删除用户", Integer.valueOf(LogTypeEnum.DELETE.getValue()));

        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(sysUserRepo.remove(userId));

//        activitiFacade.deleteUser(userId + "");
        return resultJson;
    }

}
