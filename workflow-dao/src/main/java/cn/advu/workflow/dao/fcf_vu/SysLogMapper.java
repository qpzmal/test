package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.SysLog;
import cn.advu.workflow.domain.fcf_vu.SysLogWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogMapper extends BaseDAO<SysLogWithBLOBs> {

    List<SysLogWithBLOBs> queryAll(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("sysLog")SysLog sysLog);

    Integer queryCount(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("sysLog")SysLog sysLog);
    
}