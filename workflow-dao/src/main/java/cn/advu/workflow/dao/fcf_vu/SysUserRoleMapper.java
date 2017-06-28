package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserRoleMapper extends BaseDAO<SysUserRole> {

    List<SysUserRole> queryAll();

    List<SysUserRole> queryByUser(@Param("userId")Integer userId);

    List<SysUserRole> queryByRole(@Param("roleId")Integer roleId);

    int deleteByUser(@Param("userId")Integer userId);

    int deleteByRole(@Param("roleId")Integer roleId);

    int deleteByIds(@Param("userRoleIds")List<Integer> userRoleIds);

}