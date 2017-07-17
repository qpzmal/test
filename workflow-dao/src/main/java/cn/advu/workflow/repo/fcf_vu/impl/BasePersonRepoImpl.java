package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BasePersonMapper;
import cn.advu.workflow.domain.fcf_vu.BasePerson;
import cn.advu.workflow.domain.fcf_vu.BasePersonExtend;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BasePersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通讯录
 *
 */
@Repository
public class BasePersonRepoImpl extends AbstractRepo<BasePerson> implements BasePersonRepo {

    @Autowired
    BasePersonMapper basePersonMapper;

    @Override
    protected BaseDAO<BasePerson> getSqlMapper() {
        return basePersonMapper;
    }

    @Override
    public List<BasePerson> findAll() {

        return basePersonMapper.queryAll(null);
    }

    @Override
    public List<BasePersonExtend> findDeptPerson(Integer areaId, Integer deptId) {

        return basePersonMapper.queryByDept(areaId, deptId);
    }

    @Override
    public List<BasePerson> findChildListByDept(Integer deptId) {
        return basePersonMapper.queryChildListByDept(deptId);
    }
}
