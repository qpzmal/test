package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseIndustryMapper extends BaseDAO<BaseIndustry> {
    // 以下为自定义SQL
    List<BaseIndustry> queryAll(@Param("status") Integer status);
}