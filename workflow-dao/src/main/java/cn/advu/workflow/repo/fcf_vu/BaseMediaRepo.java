package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.repo.base.IRepo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseMediaRepo extends IRepo<BaseMedia> {

    List<BaseMedia> findAll();

    List<BaseMedia> findAllActive();

    BaseMedia findByIdAndName(Integer id, String name);

    BaseMedia findByIdAndCode(Integer id, String code);

    BaseMedia findByType(Integer type);
}
