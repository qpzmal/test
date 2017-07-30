package cn.advu.workflow.web.service.workflow.manager;

import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import cn.advu.workflow.repo.fcf_vu.SysUserRoleRepo;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiqz on 2017/6/30.
 */
@Component
public class CustomGroupEntityManager extends GroupEntityManager {
    private static Logger LOGGER = LoggerFactory.getLogger(CustomGroupEntityManager.class);

    @Autowired
    private SysUserRoleRepo sysUserRoleRepo;//用于查询实际业务中角色表


    @Override
    public List<Group> findGroupsByUser(final String loginName) {
        if (loginName == null) {
            return null;
        }

        List<SysUserRole> dbGroupList = sysUserRoleRepo.findUserRoleByName(loginName);

        List<Group> gs = new ArrayList<>();
        GroupEntity g;
        String roleId;
        String activitRole = null;
        for (SysUserRole dbGroup : dbGroupList) {
            g = new GroupEntity();
            g.setRevision(1);
            g.setType("assignment");
            roleId = String.valueOf(dbGroup.getId());
//            activitRole = bindGroupWithRole.get(roleId);//此处只是根据RoleId获取RoleCode， 因实际表中无RoleCode字段，暂且如此实际，此行可注释掉
            g.setId(activitRole != null ? activitRole : roleId);
            g.setName(dbGroup.getRoleName());
            gs.add(g);
        }
        return gs;
    }
}
