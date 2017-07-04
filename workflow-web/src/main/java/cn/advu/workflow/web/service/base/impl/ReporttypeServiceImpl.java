package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BaseReportTypeMapper;
import cn.advu.workflow.domain.fcf_vu.BaseReportType;
import cn.advu.workflow.web.service.base.ReportTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class ReportTypeServiceImpl implements ReportTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportTypeServiceImpl.class);

    @Autowired
    private BaseReportTypeMapper baseReporttypeMapper;

    @Override
    public int insert(BaseReportType obj) {
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
    public List<BaseReportType> queryAll() {
        return null;
    }

    @Override
    public List<BaseReportType> queryByCondition(BaseReportType obj) {
        return null;
    }
}
