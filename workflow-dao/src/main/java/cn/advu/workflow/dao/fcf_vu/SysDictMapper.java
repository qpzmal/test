package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.SysDict;

public interface SysDictMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysDict record);

    int insertSelective(SysDict record);

    SysDict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysDict record);

    int updateByPrimaryKey(SysDict record);
}