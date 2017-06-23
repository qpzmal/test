package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BasePerson;

public interface BasePersonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasePerson record);

    int insertSelective(BasePerson record);

    BasePerson selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasePerson record);

    int updateByPrimaryKey(BasePerson record);
}