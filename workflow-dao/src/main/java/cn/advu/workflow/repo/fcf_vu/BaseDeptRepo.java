package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.domain.fcf_vu.DeptVO;
import cn.advu.workflow.repo.base.IRepo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseDeptRepo extends IRepo<BaseDept> {

    List<BaseDept> findAll();

    List<BaseDept> findAreaDept(Integer areaId);

    List<BaseDept> findChildDept(Integer areaId, Integer parentId);

    List<DeptVO> findChildDeptWithAreaNameAndParentName(Integer areaId, Integer parentId);

    BaseDept findByIdAndName(Integer id, String name);

}
