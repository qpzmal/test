package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BasePerson;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 区域操作数据库对象
 */
public interface BaseRegionMapper extends BaseDAO<BaseRegion> {

    // 以下为自定义SQL
    List<BaseRegion> queryAll();

    BaseRegion queryByIdAndName(@Param("id")Integer id, @Param("name")String name);
}