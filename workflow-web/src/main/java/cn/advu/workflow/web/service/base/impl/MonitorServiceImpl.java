package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseMonitorRequestMapper;
import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class MonitorServiceImpl implements MonitorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Autowired
    private BaseMonitorRequestMapper baseMonitorRequestMapper;

    @Override
    public int insert(BaseMonitorRequest obj) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int update(String id) {
        return 0;
    }

    @Override
    public List<BaseMonitorRequest> queryAll() {
        return baseMonitorRequestMapper.queryAll(WebConstants.ITEM_STATUS_NORMAL);
    }

    @Override
    public List<BaseMonitorRequest> queryByCondition(BaseMonitorRequest obj) {
        return null;
    }
}
