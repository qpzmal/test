package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseMonitorRequestRepo extends IRepo<BaseMonitor> {

    List<BaseMonitor> findAll();

    BaseMonitor findByIdAndName(Integer id, String name);

}
