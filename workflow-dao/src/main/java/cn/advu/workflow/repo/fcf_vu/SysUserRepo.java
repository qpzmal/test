package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRepo extends IRepo<SysUser> {

    List<SysUser> findAll();

    SysUser findByIdAndName(Integer id, String name);

    List<SysUser> findByDept(Integer deptId);
}
