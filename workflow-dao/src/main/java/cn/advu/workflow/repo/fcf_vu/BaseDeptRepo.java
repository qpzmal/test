package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseDeptRepo extends IRepo<BaseDept> {

    List<BaseDept> findAll();

    List<BaseDept> findAreaDept(Integer areaId);
}
