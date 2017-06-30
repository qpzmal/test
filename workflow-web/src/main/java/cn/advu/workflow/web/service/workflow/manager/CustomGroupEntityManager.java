package cn.advu.workflow.web.service.workflow.manager;

import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by weiqz on 2017/6/30.
 */
@Component
public class CustomGroupEntityManager extends GroupEntityManager {
    private static Logger LOGGER = LoggerFactory.getLogger(CustomGroupEntityManager.class);

//    @Autowired
//    private UserMapper userMapper;//用于查询实际业务中用户表、角色等表


//    @Override
//    public List<Group> findGroupsByUser(final String userCode) {
//        if (userCode == null)
//            return null;
//
//        List<Role> bGroupList = userMapper.getGroupByUserName(userCode);
//
//        List<Group> gs = new java.util.ArrayList<>();
//        GroupEntity g;
//        String roleId;
//        String activitRole;
//        for (Role bGroup : bGroupList) {
//            g = new GroupEntity();
//            g.setRevision(1);
//            g.setType("assignment");
//            roleId = String.valueOf(bGroup.getRoleID());
//            activitRole = bindGroupWithRole.get(roleId);//此处只是根据RoleId获取RoleCode， 因实际表中无RoleCode字段，暂且如此实际，此行可注释掉
//            g.setId(activitRole != null ? activitRole : roleId);
//            g.setName(bGroup.getRoleName());
//            gs.add(g);
//        }
//        return gs;
//    }
}
