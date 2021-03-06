package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.SysLog;
import cn.advu.workflow.domain.fcf_vu.SysLogWithBLOBs;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysLogRepo extends IRepo<SysLogWithBLOBs> {

    List<SysLogWithBLOBs> findAllLog(String startDate, String endDate, SysLog sysLog);

    Integer count(String startDate, String endDate, SysLog sysLog);


}
