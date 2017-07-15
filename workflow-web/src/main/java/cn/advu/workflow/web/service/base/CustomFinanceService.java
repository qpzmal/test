package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 区域结算信息
 * Created by weiqz on 2017/6/25.
 */
public interface CustomFinanceService {

   ResultJson<List<BaseCustomFinance>> findByCustom(Integer customId);

   ResultJson<Integer> add(BaseCustomFinance baseCustomFinance);

   ResultJson<Void> update(BaseCustomFinance baseCustomFinance);

   ResultJson<BaseCustomFinance> findById(Integer id);

   ResultJson<Void> remove(Integer id);
}
