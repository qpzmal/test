package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;

public interface BaseExecuteOrderMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseExecuteOrder record);

    int insertSelective(BaseExecuteOrder record);

    BaseExecuteOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseExecuteOrder record);

    int updateByPrimaryKey(BaseExecuteOrder record);
}