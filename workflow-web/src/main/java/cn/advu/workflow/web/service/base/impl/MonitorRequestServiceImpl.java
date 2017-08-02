package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.enums.LogTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.repo.fcf_vu.BaseMonitorRequestRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.manager.MonitorManager;
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
    @Autowired
    MonitorManager monitorManager;
    @Autowired
    BizLogManager bizLogManager;

    @Override
    public ResultJson<Integer> addMonitorRequest(BaseMonitor baseMonitorRequest) {
        if (monitorManager.isNameDuplicated(null, baseMonitorRequest.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        Integer insertCount = baseMonitorRequestRepo.addSelective(baseMonitorRequest);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建监测机构失败!");
        }
        // log
        bizLogManager.addBizLog(baseMonitorRequestRepo.findOne(baseMonitorRequest.getId()), "监测机构管理/添加监测机构", Integer.valueOf(LogTypeEnum.ADD.getValue()));

        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> updateMonitorRequest(BaseMonitor baseMonitorRequest) {

        if (monitorManager.isNameDuplicated(baseMonitorRequest.getId(), baseMonitorRequest.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }

        BaseMonitor oldBaseMonitor = baseMonitorRequestRepo.findOne(baseMonitorRequest.getId());

        Integer updateCount = baseMonitorRequestRepo.updateSelective(baseMonitorRequest);
        if(updateCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新监测机构失败!");
        }

        // log
        bizLogManager.addBizLog(oldBaseMonitor, "监测机构管理/更新监测机构", Integer.valueOf(LogTypeEnum.UPDATE.getValue()));
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<BaseMonitor>> findAll() {
        ResultJson<List<BaseMonitor>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMonitorRequestRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<BaseMonitor> findById(Integer id) {
        ResultJson<BaseMonitor> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMonitorRequestRepo.findOne(id));
        return result;
    }

}
