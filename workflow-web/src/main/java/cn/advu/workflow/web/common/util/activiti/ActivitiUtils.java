package cn.advu.workflow.web.common.util.activiti;

import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weiqz on 2017/7/30.
 */
public class ActivitiUtils {

    public static UserEntity toActivitiUser(SysUser dbUser){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(dbUser.getId().toString());
        userEntity.setFirstName(dbUser.getUserName());
        userEntity.setLastName(dbUser.getLoginName());
        userEntity.setPassword(dbUser.getPassword());
        userEntity.setEmail(dbUser.getEmail());
        userEntity.setRevision(1);
        return userEntity;
    }

    public static GroupEntity toActivitiGroup(SysUserRole userRole){
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setRevision(1);
        groupEntity.setType("assignment");

        groupEntity.setId(userRole.getActivitiName());
        groupEntity.setName(userRole.getRoleName());
        return groupEntity;
    }

    public static List<Group> toActivitiGroups(List<SysUserRole> userRoleList){

        List<org.activiti.engine.identity.Group> groupEntitys = new ArrayList<Group>();

        for (SysUserRole userRole : userRoleList) {
            GroupEntity groupEntity = toActivitiGroup(userRole);
            groupEntitys.add(groupEntity);
        }
        return groupEntitys;
    }
}
