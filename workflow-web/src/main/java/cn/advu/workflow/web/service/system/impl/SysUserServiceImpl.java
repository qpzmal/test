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

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService{
    private static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

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
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        return rj;
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
        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(sysUserRepo.remove(userId));
        return resultJson;
    }

}
