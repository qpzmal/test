package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 媒体信息
 * Created by weiqz on 2017/6/25.
 */
public interface MediaService {


    /**
     * 返回全部媒体
     *
     * @return
     */
    ResultJson<List<BaseMedia>> findAll();

    /**
     * 新增媒体
     *
     * @param baseMedia
     * @return
     */
    ResultJson<Integer> addMedia(BaseMedia baseMedia);

    /**
     * 更新媒体
     *
     * @param baseMedia
     * @return
     */
    ResultJson<Integer> updateMedia(BaseMedia baseMedia);

    /**
     * 返回当前媒体
     *
     * @param id
     * @return
     */
    ResultJson<BaseMedia> findById(Integer id);
}
