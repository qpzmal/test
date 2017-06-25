package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseDept;

import java.util.List;

/**
 * 部门架构
 * Created by weiqz on 2017/6/25.
 */
public interface DeptService {

    int insert(BaseDept obj);
    int delete(String id);
    int update(String id);
    List<BaseDept> queryAll();

    List<BaseDept> queryByCondition(BaseDept obj);
}
