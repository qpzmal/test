package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;

import java.util.List;

/**
 * 监测机构
 * Created by weiqz on 2017/6/25.
 */
public interface MonitorService {

    int insert(BaseMonitorRequest obj);
    int delete(String id);
    int update(String id);
    List<BaseMonitorRequest> queryAll();

    List<BaseMonitorRequest> queryByCondition(BaseMonitorRequest obj);
}
