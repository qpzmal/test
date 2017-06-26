package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 监测机构
 * Created by weiqz on 2017/6/25.
 */
public interface MonitorRequestService {

    ResultJson<Integer> addMonitorRequest(BaseMonitorRequest baseMonitorRequest);
    ResultJson<List<BaseMonitorRequest>> findAll();
    ResultJson<BaseMonitorRequest> queryById(String id);

}
