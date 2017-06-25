package cn.advu.workflow.web.service.system;

import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.common.ResultJson;

public interface SysUserService {

    ResultJson<Object> add(SysUser user);

//    ResultJson<Object> add(SysUser user, Integer role);

//    ResultJson<List<SysUser>> getAll(int status);
//
//    SysUser getById(Integer userId);
//
//    ResultJson<Object> edit(SysUser user, Integer roleId);
}
