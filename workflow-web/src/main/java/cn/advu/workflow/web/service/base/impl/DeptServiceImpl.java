package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseDeptMapper;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.web.service.base.DeptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class DeptServiceImpl implements DeptService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private BaseDeptMapper baseDeptMapper;

    @Override
    public int insert(BaseDept obj) {
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
    public List<BaseDept> queryAll() {
        return null;
    }

    @Override
    public List<BaseDept> queryByCondition(BaseDept obj) {
        return null;
    }
}
