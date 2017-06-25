package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseIndustryMapper;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.web.service.base.IndustryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class IndustryServiceImpl implements IndustryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndustryServiceImpl.class);

    @Autowired
    private BaseIndustryMapper baseIndustryMapper;

    @Override
    public int insert(BaseIndustry obj) {
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
    public List<BaseIndustry> queryAll() {
        return null;
    }

    @Override
    public List<BaseIndustry> queryByCondition(BaseIndustry obj) {
        return null;
    }
}
