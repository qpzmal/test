package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseReporttype;

public interface BaseReporttypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseReporttype record);

    int insertSelective(BaseReporttype record);

    BaseReporttype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseReporttype record);

    int updateByPrimaryKey(BaseReporttype record);
}