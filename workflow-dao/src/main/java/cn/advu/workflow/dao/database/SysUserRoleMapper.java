package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper extends ISqlMapper {
    int add(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
