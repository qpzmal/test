package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.SysRoleFuction;

public interface SysRoleFuctionMapper  extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRoleFuction record);

    int insertSelective(SysRoleFuction record);

    SysRoleFuction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRoleFuction record);

    int updateByPrimaryKey(SysRoleFuction record);
}