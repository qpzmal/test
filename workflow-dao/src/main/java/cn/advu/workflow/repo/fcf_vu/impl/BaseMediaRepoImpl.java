package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseAreaMapper;
import cn.advu.workflow.dao.fcf_vu.BaseMediaMapper;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseAreaRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class BaseMediaRepoImpl extends AbstractRepo<BaseMedia> implements BaseMediaRepo {

    @Autowired
    BaseMediaMapper baseMediaMapper;

    @Override
    protected BaseDAO<BaseMedia> getSqlMapper() {
        return baseMediaMapper;
    }

    @Override
    public List<BaseMedia> findAll() {
        return baseMediaMapper.queryAll(null);
    }
}
