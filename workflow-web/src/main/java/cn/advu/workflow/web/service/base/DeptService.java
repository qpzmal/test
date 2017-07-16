package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.domain.fcf_vu.DeptVO;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.dto.TreeNode;

import java.util.Collection;
import java.util.List;

/**
 * 部门架构
 * Created by weiqz on 2017/6/25.
 */
public interface DeptService {

    ResultJson<List<BaseDept>> findAll();

    ResultJson<List<BaseDept>> findAreaDept(Integer areaId);

    ResultJson<List<BaseDept>> findChildDept(Integer areaId, Integer parentId);

    ResultJson<List<DeptVO>> findChildDeptWithName(Integer areaId, Integer parentId);

    ResultJson<Integer> add(BaseDept baseDept);

    ResultJson<Void> update(BaseDept baseDept);

    ResultJson<BaseDept> findById(Integer id);

    ResultJson<Collection<TreeNode>> findAreaDeptNodeList(Integer areaId);

    ResultJson<Collection<TreeNode>> findDeptNodeList(List<BaseDept> deptList);

}
