package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseArea;

public interface BaseAreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseArea record);

    int insertSelective(BaseArea record);

    BaseArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseArea record);

    int updateByPrimaryKey(BaseArea record);
}