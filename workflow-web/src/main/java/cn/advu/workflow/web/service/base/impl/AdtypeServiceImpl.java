package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseAdtypeMapper;
import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.web.service.base.AdtypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class AdtypeServiceImpl implements AdtypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdtypeServiceImpl.class);

    @Autowired
    private BaseAdtypeMapper baseAdtypeMapper;


    @Override
    public int insert(BaseAdtype obj) {
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
    public List<BaseAdtype> queryAll() {
        return null;
    }

    @Override
    public List<BaseAdtype> queryByCondition(BaseAdtype obj) {
        return null;
    }
}
