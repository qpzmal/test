package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseMonitorRequestRepo extends IRepo<BaseMonitorRequest> {

    List<BaseMonitorRequest> findAll();
    BaseMonitorRequest queryById(String id);
}
