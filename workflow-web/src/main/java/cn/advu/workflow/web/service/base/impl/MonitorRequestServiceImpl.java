package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import cn.advu.workflow.repo.fcf_vu.BaseMonitorRequestRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.MonitorRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class MonitorRequestServiceImpl implements MonitorRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorRequestServiceImpl.class);

    @Autowired
    private BaseMonitorRequestRepo baseMonitorRequestRepo;


    @Override
    public ResultJson<Integer> addMonitorRequest(BaseMonitorRequest baseMonitorRequest) {
        Integer insertCount = baseMonitorRequestRepo.addSelective(baseMonitorRequest);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建区域失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<BaseMonitorRequest>> findAll() {
        ResultJson<List<BaseMonitorRequest>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMonitorRequestRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<BaseMonitorRequest> findById(Integer id) {
        ResultJson<BaseMonitorRequest> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMonitorRequestRepo.findOne(id));
        return result;
    }

}
