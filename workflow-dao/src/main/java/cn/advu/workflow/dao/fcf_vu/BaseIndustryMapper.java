package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseIndustry;

public interface BaseIndustryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseIndustry record);

    int insertSelective(BaseIndustry record);

    BaseIndustry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseIndustry record);

    int updateByPrimaryKey(BaseIndustry record);
}