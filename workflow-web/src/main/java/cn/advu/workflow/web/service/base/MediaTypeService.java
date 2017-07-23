package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 媒体类型信息
 * Created by weiqz on 2017/6/25.
 */
public interface MediaTypeService {


    /**
     * 返回全部媒体类型
     *
     * @return
     */
    ResultJson<List<BaseMediaType>> findAll();

    /**
     * 新增媒体类型
     *
     * @param baseMediaType
     * @return
     */
    ResultJson<Integer> add(BaseMediaType baseMediaType);

    /**
     * 更新媒体类型
     *
     * @param baseMediaType
     * @return
     */
    ResultJson<Integer> update(BaseMediaType baseMediaType);

    /**
     * 返回当前媒体类型
     *
     * @param id
     * @return
     */
    ResultJson<BaseMediaType> findById(Integer id);


    /**
     * 返回全部媒体类型
     *
     * @return
     */
    ResultJson<List<BaseMediaType>> findActiveType();
}
