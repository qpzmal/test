package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMonitorRequestMapper extends BaseDAO<BaseMonitorRequest> {

    // 以下为自定义SQL
    List<BaseMonitorRequest> queryAll(@Param("status") Integer status);
}