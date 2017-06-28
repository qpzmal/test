package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;

public interface BaseCustomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseCustom record);

    int insertSelective(BaseCustom record);

    BaseCustom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseCustom record);

    int updateByPrimaryKey(BaseCustom record);
}