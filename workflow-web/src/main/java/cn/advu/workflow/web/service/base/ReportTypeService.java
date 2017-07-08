package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseReportType;

import java.util.List;

/**
 * 报告
 * Created by weiqz on 2017/6/25.
 */
public interface ReportTypeService {

    int insert(BaseReportType obj);
    int delete(String id);
    int update(String id);
    List<BaseReportType> queryAll();

    List<BaseReportType> queryByCondition(BaseReportType obj);
}
