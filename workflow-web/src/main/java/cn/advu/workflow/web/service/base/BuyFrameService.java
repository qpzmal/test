package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 需求单
 * Created by weiqz on 2017/6/25.
 */
public interface BuyFrameService {


    /**
     * 返回全部需求框架
     *
     * @return
     */
    ResultJson<List<BaseBuyOrderFrame>> findAll(BaseBuyOrderFrame param);

    /**
     * 新增需求框架
     *
     * @param baseBuyOrderFrame
     * @return
     */
    ResultJson<Integer> add(BaseBuyOrderFrame baseBuyOrderFrame);

    /**
     * 更新需求框架
     *
     * @param baseBuyOrderFrame
     * @return
     */
    ResultJson<Integer> update(BaseBuyOrderFrame baseBuyOrderFrame);

    /**
     * 更新需求框架--动态
     *
     * @param baseBuyOrderFrame
     * @return
     */
    ResultJson<Integer> updateSelective(BaseBuyOrderFrame baseBuyOrderFrame);

    /**
     * 返回当前需求框架
     * @param id
     * @return
     */
    ResultJson<BaseBuyOrderFrame> findById(Integer id);

    /**
     * 删除采购框架
     *
     * @param id
     * @return
     */
    ResultJson<Void> remove(Integer id);
}
