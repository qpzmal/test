package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseBuyOrderMapper;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.web.service.base.BuyOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class BuyOrderServiceImpl implements BuyOrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BuyOrderServiceImpl.class);

    @Autowired
    private BaseBuyOrderMapper baseBuyOrderMapper;

    @Override
    public int insert(BaseBuyOrder obj) {
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
    public List<BaseBuyOrder> queryAll() {
        return null;
    }

    @Override
    public List<BaseBuyOrder> queryByCondition(BaseBuyOrder obj) {
        return null;
    }
}
