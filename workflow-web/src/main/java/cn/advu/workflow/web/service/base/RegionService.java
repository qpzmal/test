package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 地域管理
 * Created by weiqz on 2017/6/25.
 */
public interface RegionService {

    /**
     * 新增地域
     *
     * @param baseRegion
     * @return
     */
    ResultJson<Integer> addRegion(BaseRegion baseRegion);

    /**
     * 更新地域
     *
     * @param baseRegion
     * @return
     */
    ResultJson<Integer> updateRegion(BaseRegion baseRegion);

    /**
     * 返回全部地域
     *
     * @return
     */
    ResultJson<List<BaseRegion>> findAll();

    /**
     * 返回当前地域
     *
     * @param id
     * @return
     */
    ResultJson<BaseRegion> findById(Integer id);

    /**
     * 删除地域
     *
     * @param id
     * @return
     */
    ResultJson<Void> remove(Integer id);

}
