package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseReport;

public interface BaseReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseReport record);

    int insertSelective(BaseReport record);

    BaseReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseReport record);

    int updateByPrimaryKey(BaseReport record);
}