package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends BaseDAO<SysRole> {

    List<SysRole> queryAll();

    SysRole queryByIdAndName(@Param("id") Integer id, @Param("name")String name);

    SysRole queryByActivitiName(@Param("activitiName") String activitiName);
}