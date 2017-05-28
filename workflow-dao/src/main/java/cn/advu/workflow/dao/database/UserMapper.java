package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends ISqlMapper {

    User findUserById(@Param("id") Long id);

    User queryAdminUserByUserNameAndUserId(@Param("username") String userName,
                                           @Param("userid") Long userid);

    User queryAdminUserByUserNameAndPassword(@Param("username") String userName,
                                             @Param("password") String password);

    void updateAdminUser(User adminUser);

}