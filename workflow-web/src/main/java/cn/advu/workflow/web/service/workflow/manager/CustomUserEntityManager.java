package cn.advu.workflow.web.service.workflow.manager;

import cn.advu.workflow.dao.fcf_vu.SysUserMapper;
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
    private SysUserMapper sysUserMapper;

    @Override
    public UserEntity findUserById(String userId) {
        UserEntity userEntity = new UserEntity();
//        com.jimubox.transformers.model.user.User cue = sysUserMapper.getUserByUserName(userId);
//        ActivitiUtils.toActivitiUser(cue);
        return userEntity;
    }

    @Override
    public List<Group> findGroupsByUser(final String userCode) {
        if (userCode == null)
            return null;

//        List<String> groupIds = userMapper.getGroupIdsByUserName(userCode);

        List<Group> gs = null;
//        gs = Utils.toActivitiGroups(groupIds);
        return gs;
    }


}