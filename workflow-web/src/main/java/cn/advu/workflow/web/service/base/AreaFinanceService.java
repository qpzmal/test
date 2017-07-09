package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.dto.TreeNode;

import java.util.Collection;
import java.util.List;

/**
 * 区域信息
 * Created by weiqz on 2017/6/25.
 */
public interface AreaFinanceService {

   ResultJson<List<BaseAreaFinance>> findByArea(Integer areaId);
}
