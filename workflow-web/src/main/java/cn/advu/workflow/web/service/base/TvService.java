package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseTv;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 厂商管理
 * Created by weiqz on 2017/6/25.
 */
public interface TvService {

    /**
     * 新增媒体分类
     *
     * @param baseTv
     * @return
     */
    ResultJson<Integer> addTv(BaseTv baseTv);

    /**
     * 返回全部媒体
     *
     * @return
     */
    ResultJson<List<BaseTv>> findAll();

    /**
     * 返回当前媒体分类
     *
     * @param id
     * @return
     */
    ResultJson<BaseTv> findById(Integer id);


}
