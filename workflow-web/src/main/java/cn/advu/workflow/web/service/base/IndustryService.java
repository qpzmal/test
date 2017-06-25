package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseIndustry;

import java.util.List;

/**
 * 行业管理
 * Created by weiqz on 2017/6/25.
 */
public interface IndustryService {

    int insert(BaseIndustry obj);
    int delete(String id);
    int update(String id);
    List<BaseIndustry> queryAll();

    List<BaseIndustry> queryByCondition(BaseIndustry obj);
}
