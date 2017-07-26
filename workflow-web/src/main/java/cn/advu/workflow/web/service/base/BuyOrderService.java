package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 媒介采购合同
 * Created by weiqz on 2017/6/25.
 */
public interface BuyOrderService {


    /**
     * 返回全部需求单
     *
     * @return
     */
    ResultJson<List<BaseBuyOrder>> findAll(BaseBuyOrder param);

    /**
     * 新增需求单
     *
     * @param baseBuyOrder
     * @return
     */
    ResultJson<Integer> add(BaseBuyOrder baseBuyOrder);

    /**
     * 更新需求单
     *
     * @param baseBuyOrder
     * @return
     */
    ResultJson<Integer> update(BaseBuyOrder baseBuyOrder);

    /**
     * 返回当前需求单
     * @param id
     * @return
     */
    ResultJson<BaseBuyOrder> findById(Integer id);

    /**
     * 删除需求单
     * @param id
     * @return
     */
    ResultJson<Void> remove(Integer id);


}
