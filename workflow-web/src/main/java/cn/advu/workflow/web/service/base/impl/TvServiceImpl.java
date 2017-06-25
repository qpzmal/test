package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseTvMapper;
import cn.advu.workflow.domain.fcf_vu.BaseTv;
import cn.advu.workflow.web.service.base.TvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class TvServiceImpl implements TvService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TvServiceImpl.class);

    @Autowired
    private BaseTvMapper baseTvMapper;

    @Override
    public int insert(BaseTv obj) {
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
    public List<BaseTv> queryAll() {
        return null;
    }

    @Override
    public List<BaseTv> queryByCondition(BaseTv obj) {
        return null;
    }
}
