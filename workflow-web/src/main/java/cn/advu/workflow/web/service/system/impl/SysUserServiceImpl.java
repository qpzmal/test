package cn.advu.workflow.web.service.system.impl;

import cn.advu.workflow.common.utils.md5.StrMD5;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.system.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl implements SysUserService{
    private static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

//    @Autowired
//    private SysUserMapper sysUserMapper;

    @Autowired
    SysUserRepo sysUserRepo;
    
    @Override
    @Transactional
    public ResultJson<Object> add(SysUser user) {
        ResultJson<Object> rj = new ResultJson<>();
        //密码加密
        user.setPassword(StrMD5.getInstance().encrypt(user.getPassword(), WebConstants.MD5_SALT));

        int result = sysUserRepo.add(user);
        
        if(result == 0){
            rj.setCode(WebConstants.OPERATION_FAILURE);
            return rj;
        }
        
//        //设定角色
//        userMapper.setRole(user.getUserId(), role, user.getCreatorId());
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        return rj;
    }

//    @Override
//    public ResultJson<List<SysUser>> getAll(int status) {
//        ResultJson<List<SysUser>> rj = null;
//        try {
//            //获得有效用户
//            List<SysUser> users = sysUserMapper.queryAllUsers(status);
//            rj = new ResultJson<>();
//            rj.setCode(WebConstants.OPERATION_SUCCESS);
//            rj.setData(users);
//        } catch (Exception e) {
//            LOGGER.error("", e);
//            return null;
//        }
//        return rj;
//    }
//
//    @Override
//    public SysUser getById(Integer userId) {
//        return sysUserMapper.selectByPrimaryKey(userId);
//    }
//
//    @Override
//    public ResultJson<Object> edit(SysUser user, Integer roleId) {
//
//        //如果密码为空不加密
//        if(user.getPassword() != null && user.getPassword() != "") {
//            user.setPassword(StrMD5.getInstance().encrypt(user.getPassword(), WebConstants.MD5_SALT));
//        }
//        //用户信息修改
//        sysUserMapper.updateByPrimaryKeySelective(user);
//        //判断是否修改权限
//        if( null != roleId ) {
////            userMapper.updateRoleByUserId(user.getUserId(),roleId);
//        }
//
//
//        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
//    }

}
