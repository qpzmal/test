package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysUserMapper extends BaseDAO<SysUser> {

    SysUser queryUserByNameAndId(@Param("username") String userName, @Param("userid") String userid);

    SysUser queryUserByNameAndPassword(@Param("username") String userName, @Param("password") String password);

    List<SysUser> queryAllUsers(@Param("item_status") int status);



}