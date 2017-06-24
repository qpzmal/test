package cn.advu.workflow.web.service;

import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.common.ResultJson;

public interface SysUserService {

    ResultJson<Void> add(SysUser user);

//    ResultJson<Object> add(SysUser user, Integer role);

//    ResultJson<List<SysUser>> getAll();

//    SysUser getById(Integer userId);

//    ResultJson<Object> edit(SysUser user, Integer roleId);
}
