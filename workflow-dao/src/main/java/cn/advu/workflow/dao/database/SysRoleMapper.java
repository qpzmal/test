package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends ISqlMapper {

    List<SysRole> getAll();

    int add(@Param("role") SysRole role);

    int clearPermissionsByName(@Param("roleId") Integer roleId);

    Integer getIdByName(@Param("roleName") String roleName);
}
