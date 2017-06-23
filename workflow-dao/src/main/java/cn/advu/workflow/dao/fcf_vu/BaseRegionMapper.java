package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseRegion;

public interface BaseRegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseRegion record);

    int insertSelective(BaseRegion record);

    BaseRegion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseRegion record);

    int updateByPrimaryKey(BaseRegion record);
}