package cn.advu.workflow.web.service.system.impl;

import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.domain.fcf_vu.SysRoleFuction;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.repo.fcf_vu.SysRoleFunctionRepo;
import cn.advu.workflow.repo.fcf_vu.SysRoleRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRoleRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.facade.workflow.ActivitiFacade;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.manager.RoleManager;
import cn.advu.workflow.web.service.system.SysRoleService;
import cn.advu.workflow.web.util.AssertUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    private static Logger LOGGER = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysRoleRepo sysRoleRepo;
    @Autowired
    private SysUserRoleRepo sysUserRoleRepo;

    @Autowired
    RoleManager roleManager;

//    @Autowired
//    ActivitiFacade activitiFacade;

    @Autowired
    BizLogManager bizLogManager;

    @Autowired
    private SysRoleFunctionRepo sysRoleFunctionRepo;

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
    public ResultJson<Void> auth(List<Integer> functionIds, Integer roleId) {

        ResultJson<Void> rj = new ResultJson(WebConstants.OPERATION_SUCCESS);

        // 校验
        if (functionIds == null || functionIds.isEmpty()) {
            rj.setCode(WebConstants.OPERATION_FAILURE);
            rj.setInfo("没有给角色赋予任何权限！");
            return rj;
        }
        if (roleId == null) {
            rj.setCode(WebConstants.OPERATION_FAILURE);
            rj.setInfo("没有设置角色！");
            return rj;
        }

        // 业务处理
        List<SysRoleFuction> updateBeforeFuctionList = sysRoleFunctionRepo.findByRole(roleId);
        if (updateBeforeFuctionList == null || updateBeforeFuctionList.isEmpty()) {
            addRoleFunction(functionIds, roleId);
        }

        List<Integer> addFunctionList = new LinkedList<>();
        for (Integer functionId : functionIds) {

            if (functionId == null) {
                continue;
            }

            boolean funcitonIsExists = false;
            for (int findFunctionIndex = 0; findFunctionIndex < updateBeforeFuctionList.size(); findFunctionIndex++) {
                if (functionId.equals(updateBeforeFuctionList.get(findFunctionIndex).getFunctionId())) {
                    funcitonIsExists = true;
                    updateBeforeFuctionList.remove(findFunctionIndex);
                    break;
                }
            }

            if (!funcitonIsExists) {
                addFunctionList.add(functionId);
            }
        }
        removeRoleFunction(updateBeforeFuctionList);
        addRoleFunction(addFunctionList, roleId);



        return rj;
    }

    @Override
    public ResultJson<List<Integer>> findRoleFuntionList(Integer roleId) {

        ResultJson<List<Integer>> rj = new ResultJson(WebConstants.OPERATION_SUCCESS);
        List<Integer> roleFunctionList = new LinkedList<>();
        rj.setData(roleFunctionList);

        List<SysRoleFuction> roleFuctionList = sysRoleFunctionRepo.findByRole(roleId);
        if (roleFuctionList == null || roleFuctionList.isEmpty()) {
            return rj;
        }

        for (SysRoleFuction roleFuction : roleFuctionList) {
            roleFunctionList.add(roleFuction.getFunctionId());
        }

        return rj;
    }

    @Override
    @Transactional
    public ResultJson<Object> addRole(SysRole sysRole) {

        // 角色名称不能重复
        String roleName = sysRole.getName();
        AssertUtil.assertNotNull(roleName, MessageConstants.ROLE_NAME_IS_NULL);
        if (roleManager.isNameDuplicated(sysRole.getId(), roleName)) {
            throw new ServiceException(MessageConstants.ROLE_NAME_IS_DUPLICATED);
        }

        int result = sysRoleRepo.addSelective(sysRole);//添加角色
//        activitiFacade.createGroup(sysRole);
        if(result != 1){
            throw new ServiceException("创建角色失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Object> updateRole(SysRole sysRole) {

        Integer id = sysRole.getId();
        AssertUtil.assertNotNull(id);

        // 角色名称不能为空，不能重复
        String roleName = sysRole.getName();
        AssertUtil.assertNotNullOrEmpty(roleName, MessageConstants.ROLE_NAME_IS_NULL);
        if (roleManager.isNameDuplicated(sysRole.getId(), roleName)) {
            throw new ServiceException(MessageConstants.ROLE_NAME_IS_DUPLICATED);
        }

        int result = sysRoleRepo.updateSelective(sysRole);
        if(result != 1){
            throw new ServiceException("修改角色失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Void> removeRole(Integer id) {
        AssertUtil.assertNotNull(id);

        // ROLE 下有没有User,如果有则不能删除。
        List<SysUserRole> sysUserRoleList = sysUserRoleRepo.findRoleUser(id);
        if (sysUserRoleList != null && !sysUserRoleList.isEmpty()) {
            throw new ServiceException(MessageConstants.ROLE_IS_USING);
        }

        // 删除角色，则，角色下的function也要删除。
        sysRoleFunctionRepo.removeByRole(id);

        SysRole sysRole = sysRoleRepo.findOne(id);
        int updateCount = sysRoleRepo.logicRemove(sysRole);
        if(updateCount != 1) {
            throw new ServiceException(MessageConstants.ROLE_IS_NOT_EXISTS);
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<Integer>> addUserRole(Integer userId, List<Integer> roleIds) {

        List<Integer> activeRoleIds = addUserRoleInner(userId, roleIds);
        ResultJson<List<Integer>> resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(activeRoleIds);
        return resultJson;
    }


    @Override
    public ResultJson<Void> updateUserRole(Integer userId, List<Integer> roleIds) {
        List<SysUserRole> exsitsUserRoles = sysUserRoleRepo.findUserRole(userId);
        List<Integer> removeUserRoleIds = new ArrayList<>();
        for (SysUserRole sysUserRole : exsitsUserRoles) {
            Integer userRoleId = sysUserRole.getRoles();
            if (!roleIds.contains(userRoleId)) {
                removeUserRoleIds.add(sysUserRole.getId());
            }
            roleIds.remove(userRoleId);
        }
        addUserRoleInner(userId, roleIds);
        removeUserRole(removeUserRoleIds);
        ResultJson<Void> resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        return resultJson;
    }

    /**
     * 删除特定用户角色
     *
     * @param userRoleIds
     */
    private void removeUserRole(List<Integer> userRoleIds) {
        if(userRoleIds == null || userRoleIds.isEmpty()) {
            return;
        }
        sysUserRoleRepo.removeUserRole(userRoleIds);
    }

    /**
     * 给用户赋予角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    private List<Integer> addUserRoleInner(Integer userId, List<Integer> roleIds) {
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
        return activeRoleIds;
    }

    /**
     * 给角色追加权限
     *
     * @param functionIds
     * @param roleId
     */
    private void addRoleFunction(List<Integer> functionIds, Integer roleId) {

        if (functionIds != null && !functionIds.isEmpty()) {
            for (Integer functionId : functionIds) {
                SysRoleFuction sysRoleFuction = new SysRoleFuction();
                sysRoleFuction.setFunctionId(functionId);
                sysRoleFuction.setRoleId(roleId);
                sysRoleFunctionRepo.addSelective(sysRoleFuction);
                bizLogManager.addSaveTypeBizLog(sysRoleFunctionRepo.findOne(sysRoleFuction.getId()));
            }
        }
    }

    /**
     * 批量删除角色权限
     *
     * @param sysRoleFuctionList
     */
    private void removeRoleFunction(List<SysRoleFuction> sysRoleFuctionList) {

        if (sysRoleFuctionList != null && !sysRoleFuctionList.isEmpty()) {
            List<Integer> ids = new LinkedList<>();
            for (SysRoleFuction sysRoleFuction : sysRoleFuctionList) {
                ids.add(sysRoleFuction.getId());
            }
            sysRoleFunctionRepo.removeByIds(ids);
            bizLogManager.addDeleteTypeBizLog(sysRoleFuctionList);
        }

    }

}
