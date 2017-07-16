package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.SysRoleFuction;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleFunctionRepo extends IRepo<SysRoleFuction> {

    List<SysRoleFuction> findByRole(Integer roleId);

    void removeByIds(List<Integer> ids);

    void removeByRole(Integer roleId);
}
