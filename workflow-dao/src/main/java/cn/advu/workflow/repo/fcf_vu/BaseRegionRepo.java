package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseRegionRepo extends IRepo<BaseRegion> {

    List<BaseRegion> findAll();

    List<BaseRegion> findAllActiveRegion();
}
