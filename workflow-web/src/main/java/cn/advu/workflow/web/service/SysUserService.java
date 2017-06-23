package cn.advu.workflow.web.service;

import cn.advu.workflow.domain.database.SysUser;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

public interface SysUserService {

    ResultJson<Object> add(SysUser user, Integer role);

    ResultJson<List<SysUser>> getAll();

    SysUser getById(Integer userId);

    ResultJson<Object> edit(SysUser user, Integer roleId);
}
