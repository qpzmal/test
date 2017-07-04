package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 监测机构
 * Created by weiqz on 2017/6/25.
 */
public interface MonitorRequestService {

    /**
     * 新增监测机构
     *
     * @param baseMonitorRequest
     * @return
     */
    ResultJson<Integer> addMonitorRequest(BaseMonitor baseMonitorRequest);

    /**
     * 更新监测机构
     *
     * @param baseMonitorRequest
     * @return
     */
    ResultJson<Integer> updateMonitorRequest(BaseMonitor baseMonitorRequest);

    /**
     * 返回全部监测机构
     *
     * @return
     */
    ResultJson<List<BaseMonitor>> findAll();

    /**
     * 返回当前监测机构
     *
     * @param id
     * @return
     */
    ResultJson<BaseMonitor> findById(Integer id);

}
