package cn.advu.workflow.web.service.system;

import cn.advu.workflow.domain.fcf_vu.SysInfo;
import cn.advu.workflow.web.common.ResultJson;

public interface SysInfoService {

    /**
     * 返回系统信息
     *
     * @return
     */
    ResultJson<SysInfo> querySysInfo();

    /**
     * 更新系统信息
     * @param sysInfo
     * @return
     */
    ResultJson<Integer> updateSelective(SysInfo sysInfo);

}
