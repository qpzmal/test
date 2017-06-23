package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseDept;

public interface BaseDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseDept record);

    int insertSelective(BaseDept record);

    BaseDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseDept record);

    int updateByPrimaryKey(BaseDept record);
}