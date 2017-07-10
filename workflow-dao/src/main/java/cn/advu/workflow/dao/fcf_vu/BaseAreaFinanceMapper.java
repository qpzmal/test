package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAreaFinanceMapper extends BaseDAO<BaseAreaFinance> {

    List<BaseAreaFinance> queryByArea(@Param("areaId") Integer areaId);
}