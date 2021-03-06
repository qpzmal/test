package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.repo.base.IRepo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseMediaTypeRepo extends IRepo<BaseMediaType> {

    List<BaseMediaType> findAll();

    List<BaseMediaType> findActiveType();

    BaseMediaType findByIdAndName(Integer id, String name);
}
