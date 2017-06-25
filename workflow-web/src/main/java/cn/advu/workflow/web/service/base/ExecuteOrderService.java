package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;

import java.util.List;

/**
 * 客户需求单
 * Created by weiqz on 2017/6/25.
 */
public interface ExecuteOrderService {

    int insert(BaseExecuteOrder obj);
    int delete(String id);
    int update(String id);
    List<BaseExecuteOrder> queryAll();

    List<BaseExecuteOrder> queryByCondition(BaseExecuteOrder obj);
}
