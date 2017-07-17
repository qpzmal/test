package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseIndustryMapper;
import cn.advu.workflow.domain.enums.ItemStatusEnum;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseIndustryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class BaseIndustryRepoImpl extends AbstractRepo<BaseIndustry> implements BaseIndustryRepo {

    @Autowired
    BaseIndustryMapper baseIndustryMapper;

    @Override
    protected BaseDAO<BaseIndustry> getSqlMapper() {
        return baseIndustryMapper;
    }

    @Override
    public List<BaseIndustry> findAll() {
        return baseIndustryMapper.queryAll(null);
    }

    @Override
    public List<BaseIndustry> findEnabledAll() {
        return baseIndustryMapper.queryAll(Integer.valueOf(ItemStatusEnum.ACTIVE.getValue()));
    }
}
