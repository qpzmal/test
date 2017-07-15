package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 区域结算信息
 * Created by weiqz on 2017/6/25.
 */
public interface AreaFinanceService {

   ResultJson<List<BaseAreaFinance>> findByArea(Integer areaId);

   ResultJson<Integer> add(BaseAreaFinance baseAreaFinance);

   ResultJson<Void> update(BaseAreaFinance baseAreaFinance);

   ResultJson<BaseAreaFinance> findById(Integer id);

   ResultJson<Void> remove(Integer id);
}
