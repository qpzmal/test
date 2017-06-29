package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 财务指标信息
 * Created by weiqz on 2017/6/25.
 */
public interface FinancialindexService {


    /**
     * 返回全部财务指标
     *
     * @return
     */
    ResultJson<List<BaseFinancialindex>> findAll();

    /**
     * 新增财务指标
     *
     * @param baseFinancialindex
     * @return
     */
    ResultJson<Integer> add(BaseFinancialindex baseFinancialindex);

    /**
     * 更新财务指标
     *
     * @param baseFinancialindex
     * @return
     */
    ResultJson<Integer> update(BaseFinancialindex baseFinancialindex);

    /**
     * 返回当前财务指标
     *
     * @param id
     * @return
     */
    ResultJson<BaseFinancialindex> findById(Integer id);
}
