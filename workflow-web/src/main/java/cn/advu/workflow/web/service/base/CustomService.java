package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.domain.searchVO.CustomSearchVO;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 客户信息
 * Created by weiqz on 2017/6/25.
 */
public interface CustomService {


    /**
     * 返回全部客户
     *
     * @return
     */
    ResultJson<List<BaseCustom>> findAll();

    /**
     * 新增客户
     *
     * @param baseCustom
     * @return
     */
    ResultJson<Integer> add(BaseCustom baseCustom);

    /**
     * 更新客户
     *
     * @param baseCustom
     * @return
     */
    ResultJson<Integer> update(BaseCustom baseCustom);

    /**
     * 删除客户
     * @param id
     * @return
     */
    ResultJson<Void> remove(Integer id);

    /**
     * 返回当前客户
     * @param id
     * @return
     */
    ResultJson<BaseCustom> findById(Integer id);

    /**
     * 返回所有直接客户
     *
     * @return
     */
    ResultJson<List<BaseCustom>> findParentCustom();

    /**
     * 根据查询条件返回客户列表
     * @return
     */
    ResultJson<List<BaseCustom>> findCustomBySearchVO(CustomSearchVO customSearchVO);

}
