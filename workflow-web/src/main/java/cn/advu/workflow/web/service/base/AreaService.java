package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.AreaVO;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.dto.TreeNode;

import java.util.Collection;
import java.util.List;

/**
 * 区域信息
 * Created by weiqz on 2017/6/25.
 */
public interface AreaService {

    /**
     * 返回全部区域
     *
     * @return
     */
    ResultJson<List<BaseArea>> findAll();

    /**
     * 新增区域
     *
     * @param baseArea
     * @return
     */
    ResultJson<Integer> addArea(BaseArea baseArea);

    /**
     * 更新区域
     *
     * @param baseArea
     * @return
     */
    ResultJson<Integer> updateArea(BaseArea baseArea);

    /**
     * 返回当前区域
     *
     * @param id
     * @return
     */
    ResultJson<BaseArea> findById(Integer id);

    /**
     * 返回公司的树状列表
     *
     * @param areaId
     * @return
     */
    ResultJson<Collection<TreeNode>> findAreaNodeList(Integer areaId);

    /**
     * 返回下级公司列表
     *
     * @param parentId
     * @return
     */
    ResultJson<List<AreaVO>> findByParent(Integer parentId);
}
