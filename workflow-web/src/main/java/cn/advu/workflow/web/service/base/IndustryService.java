package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 行业管理
 * Created by weiqz on 2017/6/25.
 */
public interface IndustryService {

    /**
     * 新增客户行业
     *
     * @param baseIndustry
     * @return
     */
    ResultJson<Integer> addIndustry(BaseIndustry baseIndustry);

    /**
     * 返回全部客户行业
     *
     * @return
     */
    ResultJson<List<BaseIndustry>> findAll();

    /**
     * 返回当前客户行业
     *
     * @param id
     * @return
     */
    ResultJson<BaseIndustry> findById(Integer id);


}
