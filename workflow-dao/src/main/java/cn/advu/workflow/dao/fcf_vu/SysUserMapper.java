package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysUserMapper extends BaseDAO<SysUser> {


    // 以下为自定义SQL

    List<SysUser> queryAll(@Param("item_status") Integer status);

    SysUser queryUserByNameAndId(@Param("username") String userName, @Param("userid") String userid);

    SysUser queryUserByNameAndPassword(@Param("username") String userName, @Param("password") String password);


    SysUser queryIdAndName(@Param("id") Integer id, @Param("name") String name);

    List<SysUser> queryByDept(@Param("deptId") Integer deptId);

}