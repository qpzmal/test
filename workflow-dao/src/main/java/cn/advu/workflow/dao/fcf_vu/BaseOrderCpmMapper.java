package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;

public interface BaseOrderCpmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseOrderCpm record);

    int insertSelective(BaseOrderCpm record);

    BaseOrderCpm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseOrderCpm record);

    int updateByPrimaryKey(BaseOrderCpm record);
}