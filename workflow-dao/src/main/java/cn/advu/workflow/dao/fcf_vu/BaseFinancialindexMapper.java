package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseFinancialindexMapper extends BaseDAO<BaseFinancialindex> {

    // 以下为自定义SQL
    List<BaseFinancialindex> queryAll(@Param("status") Integer status);

    BaseFinancialindex queryByIdAndName(@Param("id")Integer id, @Param("name")String name);

    BaseFinancialindex queryByIdAndNumber(@Param("id")Integer id, @Param("number")String number);
}