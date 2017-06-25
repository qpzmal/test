package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseReporttypeMapper;
import cn.advu.workflow.domain.fcf_vu.BaseReporttype;
import cn.advu.workflow.web.service.base.ReporttypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class ReporttypeServiceImpl implements ReporttypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReporttypeServiceImpl.class);

    @Autowired
    private BaseReporttypeMapper baseReporttypeMapper;

    @Override
    public int insert(BaseReporttype obj) {
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
    public List<BaseReporttype> queryAll() {
        return null;
    }

    @Override
    public List<BaseReporttype> queryByCondition(BaseReporttype obj) {
        return null;
    }
}
