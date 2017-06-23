package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;

public interface BaseExecuteOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseExecuteOrder record);

    int insertSelective(BaseExecuteOrder record);

    BaseExecuteOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseExecuteOrder record);

    int updateByPrimaryKey(BaseExecuteOrder record);
}