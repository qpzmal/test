package cn.advu.workflow.web.service.system.impl;

import cn.advu.workflow.common.cache.CacheCaller;
import cn.advu.workflow.common.cache.EhcacheHelper;
import cn.advu.workflow.domain.fcf_vu.SysInfo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.constants.cache.EhcacheConstants;
import cn.advu.workflow.web.manager.SysInfoMananger;
import cn.advu.workflow.web.service.system.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by weiqz on 2017/8/24.
 */
@Service
public class SysInfoServiceImpl implements SysInfoService {
    @Autowired
    private SysInfoMananger sysInfoMananger;

    private static final String SYS_INFO_ID = "1";

    @Override
    public ResultJson<SysInfo> querySysInfo() {
        SysInfo result = EhcacheHelper.getCacheAndSet(EhcacheConstants.SYS_INFO, SYS_INFO_ID, new CacheCaller<SysInfo>() {
            @Override
            public SysInfo getData() {
                return sysInfoMananger.querySysInfo();
            }
        });
        ResultJson<SysInfo> rj = new ResultJson<>();
        rj.setData(result);
        return rj;
    }

    @Override
    public ResultJson<Integer> updateSelective(SysInfo sysInfo) {
        int result = sysInfoMananger.updateSelective(sysInfo);
        EhcacheHelper.removeKey(EhcacheConstants.SYS_INFO, SYS_INFO_ID);
        ResultJson<Integer> rj = new ResultJson<>();
        rj.setData(result);
        return rj;
    }
}
