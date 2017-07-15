package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.AreaVO;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.repo.base.IRepo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAreaRepo extends IRepo<BaseArea> {

    List<BaseArea> findAll();
    List<BaseArea> findByParent(Integer parentId);
    List<AreaVO> findByParentAreaVO(Integer parentId);

}
