package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;

import java.util.List;

/**
 * 媒介采购合同
 * Created by weiqz on 2017/6/25.
 */
public interface BuyOrderService {

    int insert(BaseBuyOrder obj);
    int delete(String id);
    int update(String id);
    List<BaseBuyOrder> queryAll();

    List<BaseBuyOrder> queryByCondition(BaseBuyOrder obj);
}
