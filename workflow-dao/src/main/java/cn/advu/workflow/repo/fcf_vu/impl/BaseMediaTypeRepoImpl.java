package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseMediaMapper;
import cn.advu.workflow.dao.fcf_vu.BaseMediaTypeMapper;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class BaseMediaTypeRepoImpl extends AbstractRepo<BaseMediaType> implements BaseMediaTypeRepo {

    @Autowired
    BaseMediaTypeMapper baseMediaTypeMapper;

    @Override
    protected BaseDAO<BaseMediaType> getSqlMapper() {
        return baseMediaTypeMapper;
    }

    @Override
    public List<BaseMediaType> findAll() {
        return baseMediaTypeMapper.queryAll(null);
    }
}
