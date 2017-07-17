package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.repo.fcf_vu.BaseAreaRepo;
import cn.advu.workflow.repo.fcf_vu.BaseRegionRepo;
import cn.advu.workflow.web.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/14.
 */
@Component
public class RegionManager {

    @Autowired
    BaseRegionRepo baseRegionRepo;

    public List<BaseRegion> findAllActiveRegionList() {
        return baseRegionRepo.findAllActiveRegion();
    }

}
