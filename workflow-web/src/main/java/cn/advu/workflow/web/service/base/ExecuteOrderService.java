package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 需求单
 * Created by weiqz on 2017/6/25.
 */
public interface ExecuteOrderService {


    /**
     * 返回全部需求单
     *
     * @return
     */
    ResultJson<List<BaseExecuteOrder>> findAll(BaseExecuteOrder param);


    /**
     * 返回全部需求单
     *
     * @return
     */
    ResultJson<List<BaseExecuteOrder>> findAllUnFinished();

    /**
     * 新增需求单
     *
     * @param baseExecuteOrder
     * @return
     */
    ResultJson<Integer> add(BaseExecuteOrder baseExecuteOrder);

    /**
     * 更新需求单
     *
     * @param baseExecuteOrder
     * @return
     */
    ResultJson<Integer> update(BaseExecuteOrder baseExecuteOrder);

    /**
     * 删除需求单
     * @param id
     * @return
     */
    ResultJson<Void> remove(Integer id);

    /**
     * 返回当前需求单
     * @param id
     * @return
     */
    ResultJson<BaseExecuteOrder> findById(Integer id);
}
