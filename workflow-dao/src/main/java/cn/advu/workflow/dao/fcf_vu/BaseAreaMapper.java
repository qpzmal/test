package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseTreeDAO;
import cn.advu.workflow.domain.fcf_vu.AreaVO;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAreaMapper extends BaseTreeDAO<BaseArea> {

    // 以下为自定义SQL
    List<BaseArea> queryAll(@Param("status") Integer status);

    List<BaseArea> queryByParent(@Param("parentId") Integer parentId);

    // 根据parent查询列表
    List<AreaVO> queryByParentAreaVO(@Param("parentId") Integer parentId);

}