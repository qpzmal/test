package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseAreaMapper;
import cn.advu.workflow.dao.fcf_vu.BaseMediaMapper;
import cn.advu.workflow.domain.enums.ItemStatusEnum;
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

    @Override
    public List<BaseMedia> findAllActive() {
        return baseMediaMapper.queryAll(Integer.valueOf(ItemStatusEnum.ACTIVE.getValue()));
    }

    @Override
    public BaseMedia findByIdAndName(Integer id, String name) {
        return baseMediaMapper.queryByIdAndName(id, name);
    }

    @Override
    public BaseMedia findByIdAndCode(Integer id, String code) {
        return baseMediaMapper.queryByIdAndCode(id, code);
    }

    @Override
    public BaseMedia findByType(Integer type) {
        return baseMediaMapper.queryByType(type);
    }
}
