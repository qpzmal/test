package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 需求单
 * Created by weiqz on 2017/6/25.
 */
public interface SaleFrameService {


    /**
     * 返回全部需求框架
     *
     * @return
     */
    ResultJson<List<BaseExecuteOrderFrame>> findAll(BaseExecuteOrderFrame param);

    /**
     * 新增需求框架
     *
     * @param baseExecuteOrderFrame
     * @return
     */
    ResultJson<Integer> add(BaseExecuteOrderFrame baseExecuteOrderFrame);

    /**
     * 更新需求框架
     *
     * @param baseExecuteOrderFrame
     * @return
     */
    ResultJson<Integer> update(BaseExecuteOrderFrame baseExecuteOrderFrame);


    /**
     * 更新需求框架--动态
     *
     * @param baseExecuteOrderFrame
     * @return
     */
    ResultJson<Integer> updateSelective(BaseExecuteOrderFrame baseExecuteOrderFrame);

    /**
     * 返回当前需求框架
     * @param id
     * @return
     */
    ResultJson<BaseExecuteOrderFrame> findById(Integer id);

    /**
     * 删除需求单
     * @param id
     * @return
     */
    ResultJson<Void> remove(Integer id);
}
