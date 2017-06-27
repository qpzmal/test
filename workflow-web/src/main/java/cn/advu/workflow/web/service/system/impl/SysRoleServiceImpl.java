package cn.advu.workflow.web.service.system.impl;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.repo.fcf_vu.SysRoleRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRoleRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.system.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleRepo sysRoleRepo;
    @Autowired
    private SysUserRoleRepo sysUserRoleRepo;

    @Override
    public ResultJson<List<SysRole>> findAll() {
        ResultJson<List<SysRole>> rj = new ResultJson();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(sysRoleRepo.findAll());
        return rj;
    }

    @Override
    public ResultJson<SysRole> findByRoleId(Integer roleId) {
        ResultJson<SysRole> rj = new ResultJson(WebConstants.OPERATION_SUCCESS);
        rj.setData(sysRoleRepo.findOne(roleId));
        return rj;
    }

    @Override
    public ResultJson<List<SysUserRole>> findUserRoleAll(Integer userId) {
        ResultJson<List<SysUserRole>> rj = new ResultJson(WebConstants.OPERATION_SUCCESS);
        rj.setData(sysUserRoleRepo.findUserRole(userId));
        return rj;
    }

    @Override
    public ResultJson<Integer> removeUserRole(Integer userId) {
        ResultJson<Integer> rj = new ResultJson(WebConstants.OPERATION_SUCCESS);
        rj.setData(sysUserRoleRepo.removeUserRole(userId));
        return rj;
    }

    @Override
    @Transactional
    public ResultJson<Object> addRole(SysRole sysRole) {
        int result = sysRoleRepo.addSelective(sysRole);//添加角色
        if(result != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建角色失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<Integer>> addUserRole(Integer userId, List<Integer> roleIds) {

        List<SysRole> sysRoles = sysRoleRepo.findAll();
        List<Integer> activeRoleIds = new ArrayList<>();

        for (Integer roleId : roleIds) {
            boolean isActiveRole = false;

            for (SysRole sysRole : sysRoles) {
                if (sysRole.getId().equals(roleId)) {
                    isActiveRole = true;
                    break;
                }
            }
            if (isActiveRole) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setAdmins(userId);
                sysUserRole.setRoles(roleId);
                sysUserRoleRepo.addSelective(sysUserRole);
                activeRoleIds.add(roleId);
            }
        }
        ResultJson<List<Integer>> resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(activeRoleIds);
        return resultJson;
    }

}
