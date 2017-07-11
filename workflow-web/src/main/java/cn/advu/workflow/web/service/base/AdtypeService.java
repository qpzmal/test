package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 广告类型
 * Created by weiqz on 2017/6/25.
 */
public interface AdtypeService {

    /**
     * 新增广告类型
     *
     * @param baseAdtype
     * @return
     */
    ResultJson<Integer> addAdtype(BaseAdtype baseAdtype);

    /**
     * 返回全部广告类型
     *
     * @return
     */
    ResultJson<List<BaseAdtype>> findAll();

    /**
     * 返回当前广告类型
     *
     * @param id
     * @return
     */
    ResultJson<BaseAdtype> findById(Integer id);

    /**
     * 更新广告类型
     *
     * @param baseAdtype
     * @return
     */
    ResultJson<Integer> udpateAdtype(BaseAdtype baseAdtype);

    /**
     * 删除广告类型
     *
     * @param id
     * @return
     */
    ResultJson<Void> remove(Integer id);

}
