package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.repo.fcf_vu.BaseAdtypeRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/18.
 */
@Component
public class AdtypeMananger {

    @Autowired
    private BaseAdtypeRepo baseAdtypeRepo;

    /**
     * 返回可用的广告类型列表
     *
     * @return
     */
    public List<BaseAdtype> findAllActive() {
        return  baseAdtypeRepo.findAllActive();
    }

    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseAdtype baseAdtype = baseAdtypeRepo.findByIdAndName(id, name);
        if (baseAdtype != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }
}
