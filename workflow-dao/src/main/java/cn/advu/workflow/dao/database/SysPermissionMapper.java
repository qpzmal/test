package cn.advu.workflow.dao.database;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.database.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysPermissionMapper extends ISqlMapper {
    /**
     * 添加新记录
     *
     * @param permission
     * @return
     */
    int add(@Param("permission") SysPermission permission);

    List<SysPermission> getByRoleId(@Param("roleId") Integer roleId);

    int addPermissionForRole(@Param("creatorId") Integer creatorId,
                             @Param("roleId") Integer roleId, @Param("pid") String pid);

    List<SysPermission> getAll();

    List<SysPermission> getByUserId(@Param("userId") Integer userId);

}
