package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;

public interface BaseExecuteOrderFrameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseExecuteOrderFrame record);

    int insertSelective(BaseExecuteOrderFrame record);

    BaseExecuteOrderFrame selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseExecuteOrderFrame record);

    int updateByPrimaryKey(BaseExecuteOrderFrame record);
}