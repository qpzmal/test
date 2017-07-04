package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseMonitorMapper;
import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMonitorRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 媒体
 *
 */
@Repository
public class BaseMonitorRequestRepoImpl extends AbstractRepo<BaseMonitor> implements BaseMonitorRequestRepo {

    @Autowired
    BaseMonitorMapper baseMonitorMapper;

    @Override
    protected BaseDAO<BaseMonitor> getSqlMapper() {
        return baseMonitorMapper;
    }

    @Override
    public List<BaseMonitor> findAll() {
        return baseMonitorMapper.queryAll(null);
    }

}
