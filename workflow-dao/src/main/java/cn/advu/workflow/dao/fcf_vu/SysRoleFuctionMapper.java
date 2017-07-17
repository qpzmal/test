package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.SysRoleFuction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleFuctionMapper  extends BaseDAO<SysRoleFuction> {

    List<SysRoleFuction> queryByRole(@Param("roleId") Integer roleId);

    void deleteByIds(@Param("ids") List<Integer> ids);

    void deleteByRole(@Param("roleId") Integer roleId);

}