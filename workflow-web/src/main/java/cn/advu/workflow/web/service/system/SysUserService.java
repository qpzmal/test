package cn.advu.workflow.web.service.system;

import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

public interface SysUserService {

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    ResultJson<Object> add(SysUser user);

    /**
     * 返回所有用户
     *
     * @return
     */
    ResultJson<List<SysUser>> findAll();

    /**
     * 返回当前用户
     *
     * @param userId
     * @return
     */
    ResultJson<SysUser> findByUserId(Integer userId);

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    ResultJson<Integer> remove(Integer userId);

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    ResultJson<Object> update(SysUser user);

}
