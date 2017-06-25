package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseReporttype;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseReporttypeMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseReporttype record);

    int insertSelective(BaseReporttype record);

    BaseReporttype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseReporttype record);

    int updateByPrimaryKey(BaseReporttype record);

    // 以下为自定义SQL
    List<BaseReporttype> queryAll(@Param("status") int status);
}