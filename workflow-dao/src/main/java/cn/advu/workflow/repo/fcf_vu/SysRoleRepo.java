package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.SysRole;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleRepo extends IRepo<SysRole> {

    List<SysRole> findAll();

    SysRole findByIdAndName(Integer roleId, String name);

    SysRole queryByActivitiName(String activitiName);

}
