package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseReporttype;

import java.util.List;

/**
 * 报告
 * Created by weiqz on 2017/6/25.
 */
public interface ReporttypeService {

    int insert(BaseReporttype obj);
    int delete(String id);
    int update(String id);
    List<BaseReporttype> queryAll();

    List<BaseReporttype> queryByCondition(BaseReporttype obj);
}
