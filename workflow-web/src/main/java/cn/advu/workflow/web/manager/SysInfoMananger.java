package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.SysInfo;
import cn.advu.workflow.repo.fcf_vu.SysInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangry on 17/7/18.
 */
@Component
public class SysInfoMananger {

    @Autowired
    private SysInfoRepo sysInfoRepo;

    /**
     * 目前情况，系统表只能有一条数据，且ID=1
     * @return
     */
    public SysInfo querySysInfo() {
        return  sysInfoRepo.findOne(1);
    }
    public int updateSelective(SysInfo sysInfo) {
        return sysInfoRepo.updateSelective(sysInfo);
    }
}
