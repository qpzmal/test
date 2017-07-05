package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseAreaMapper;
import cn.advu.workflow.dao.fcf_vu.BaseDeptMapper;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseAreaRepo;
import cn.advu.workflow.repo.fcf_vu.BaseDeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 部门
 *
 */
@Repository
public class BaseDeptRepoImpl extends AbstractRepo<BaseDept> implements BaseDeptRepo {

    @Autowired
    BaseDeptMapper baseDeptMapper;

    @Override
    protected BaseDAO<BaseDept> getSqlMapper() {
        return baseDeptMapper;
    }

    @Override
    public List<BaseDept> findAll() {

        return baseDeptMapper.queryAll(null);
    }

    @Override
    public List<BaseDept> findAreaDept(Integer areaId) {
        return baseDeptMapper.queryByArea(areaId);
    }

    @Override
    public List<BaseDept> findChildDept(Integer areaId, Integer parentId) {
        return baseDeptMapper.queryChildDept(areaId, parentId);
    }
}
