package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseAreaMapper;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.web.service.base.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class AreaServiceImpl implements AreaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private BaseAreaMapper baseAreaMapper;
    @Override
    public int insert(BaseArea obj) {
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
    public List<BaseArea> queryAll() {
        return null;
    }

    @Override
    public List<BaseArea> queryByCondition(BaseArea obj) {
        return null;
    }
}
