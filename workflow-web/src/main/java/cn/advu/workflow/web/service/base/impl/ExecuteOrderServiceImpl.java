package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderMapper;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class ExecuteOrderServiceImpl implements ExecuteOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteOrderServiceImpl.class);

    @Autowired
    private BaseExecuteOrderMapper baseExecuteOrderMapper;

    @Override
    public int insert(BaseExecuteOrder obj) {
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
    public List<BaseExecuteOrder> queryAll() {
        return null;
    }

    @Override
    public List<BaseExecuteOrder> queryByCondition(BaseExecuteOrder obj) {
        return null;
    }
}
