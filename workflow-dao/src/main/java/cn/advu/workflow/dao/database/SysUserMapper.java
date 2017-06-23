package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends ISqlMapper {

    int add(@Param("user") SysUser user);

    List<SysUser> getValid();

    int setRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId,
                @Param("creatorId") Integer creatorId);

    SysUser getById(@Param("userId") Integer userId);

    void updateRoleByUserId(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    int update(SysUser user);

    SysUser getByAcctAndPasswd(@Param("acctId") String acctId, @Param("passwd") String passwd);
}
