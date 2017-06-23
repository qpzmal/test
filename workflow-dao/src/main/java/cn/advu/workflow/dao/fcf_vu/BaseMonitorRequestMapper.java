package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;

public interface BaseMonitorRequestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMonitorRequest record);

    int insertSelective(BaseMonitorRequest record);

    BaseMonitorRequest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMonitorRequest record);

    int updateByPrimaryKey(BaseMonitorRequest record);
}