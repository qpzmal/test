package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.repo.fcf_vu.BaseMonitorRequestRepo;
import cn.advu.workflow.repo.fcf_vu.BaseRegionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/14.
 */
@Component
public class MonitorManager {

    @Autowired
    BaseMonitorRequestRepo baseMonitorRequestRepo;


    /**
     * 判定客户名称是否重复
     *
     * @param id
     * @param name
     * @return
     */
    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseMonitor baseMonitor = baseMonitorRequestRepo.findByIdAndName(id, name);
        if (baseMonitor != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }

}
