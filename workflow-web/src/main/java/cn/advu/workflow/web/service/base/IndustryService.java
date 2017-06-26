package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 行业管理
 * Created by weiqz on 2017/6/25.
 */
public interface IndustryService {

    ResultJson<Integer> addIndustry(BaseIndustry baseIndustry);
    ResultJson<List<BaseIndustry>> findAll();


}
