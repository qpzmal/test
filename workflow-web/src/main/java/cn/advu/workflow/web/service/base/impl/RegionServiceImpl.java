package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseRegionMapper;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.web.service.base.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class RegionServiceImpl implements RegionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);

    @Autowired
    private BaseRegionMapper baseRegionMapper;

    @Override
    public int insert(BaseRegion obj) {
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
    public List<BaseRegion> queryAll() {
        return null;
    }

    @Override
    public List<BaseRegion> queryByCondition(BaseRegion obj) {
        return null;
    }
}
