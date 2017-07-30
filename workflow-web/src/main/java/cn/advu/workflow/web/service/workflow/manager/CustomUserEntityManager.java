package cn.advu.workflow.web.service.workflow.manager;

import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.repo.fcf_vu.SysUserRepo;
import cn.advu.workflow.repo.fcf_vu.SysUserRoleRepo;
import cn.advu.workflow.web.common.util.activiti.ActivitiUtils;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by weiqz on 2017/6/30.
 */
@Component
public class CustomUserEntityManager extends UserEntityManager {
    private static Logger LOGGER = LoggerFactory.getLogger(CustomUserEntityManager.class);


    @Autowired
    private SysUserRepo sysUserRepo;//用于查询实际业务中用户表

    @Autowired
    private SysUserRoleRepo sysUserRoleRepo;//用于查询实际业务中角色表


    @Override
    public UserEntity findUserById(String userId) {
        UserEntity userEntity = new UserEntity();
        SysUser dbUser = sysUserRepo.findOne(Integer.valueOf(userId));
        ActivitiUtils.toActivitiUser(dbUser);
        return userEntity;
    }

    @Override
    public List<Group> findGroupsByUser(final String userId) {
        if (userId == null)
            return null;

        List<SysUserRole> dbGroupList = sysUserRoleRepo.findUserRole(Integer.valueOf(userId));

        List<Group> gs = ActivitiUtils.toActivitiGroups(dbGroupList);
        return gs;
    }


}