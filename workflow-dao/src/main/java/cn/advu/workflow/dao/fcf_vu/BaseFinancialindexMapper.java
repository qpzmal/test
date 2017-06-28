package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;

public interface BaseFinancialindexMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseFinancialindex record);

    int insertSelective(BaseFinancialindex record);

    BaseFinancialindex selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseFinancialindex record);

    int updateByPrimaryKey(BaseFinancialindex record);
}