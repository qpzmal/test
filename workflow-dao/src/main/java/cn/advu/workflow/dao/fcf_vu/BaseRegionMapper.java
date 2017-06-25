package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseRegionMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseRegion record);

    int insertSelective(BaseRegion record);

    BaseRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseRegion record);

    int updateByPrimaryKey(BaseRegion record);

    // 以下为自定义SQL
    List<BaseRegion> queryAll(@Param("status") int status);
}