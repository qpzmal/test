package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseReportType;

public interface BaseReportTypeMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseReportType record);

    int insertSelective(BaseReportType record);

    BaseReportType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseReportType record);

    int updateByPrimaryKey(BaseReportType record);
}