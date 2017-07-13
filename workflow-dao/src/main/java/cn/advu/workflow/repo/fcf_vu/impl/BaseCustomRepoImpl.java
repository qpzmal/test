package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseCustomMapper;
import cn.advu.workflow.domain.enums.CustomTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.utils.ValueEnumUtils;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户
 *
 */
@Repository
public class BaseCustomRepoImpl extends AbstractRepo<BaseCustom> implements BaseCustomRepo {

    @Autowired
    BaseCustomMapper baseCustomMapper;

    @Override
    protected BaseDAO<BaseCustom> getSqlMapper() {
        return baseCustomMapper;
    }

    @Override
    public List<BaseCustom> findAll() {
        return baseCustomMapper.queryAll(null);
    }

    @Override
    public List<BaseCustom> findParentCustom() {
        return null;
    }

    @Override
    public BaseCustom findByIdAndName(Integer id, String name) {
        return baseCustomMapper.queryByIdAndName(id, name);
    }

    @Override
    public List<BaseCustom> find4AChildCustom(Integer id) {
        return baseCustomMapper.query4AChildCustom(id);
    }

    @Override
    public List<BaseCustom> findAllChildCustom(Integer id) {
        return baseCustomMapper.queryAllChildCustom(id);
    }

    @Override
    public int add(BaseCustom entity) {
        int count = 0;
        if (entity != null) {

            count = getSqlMapper().insert(entity);
            updateDAparent(entity);

        }

        return count;
    }

    @Override
    public int addSelective(BaseCustom entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().insertSelective(entity);
            updateDAparent(entity);
        }
        return count;
    }

    @Override
    public int update(BaseCustom entity) {
        int count = 0;
        if (entity != null) {
            setDAparent(entity);
            count = getSqlMapper().updateByPrimaryKey(entity);
        }

        return count;
    }

    @Override
    public int updateSelective(BaseCustom entity) {
        int count = 0;
        if (entity != null) {
            setDAparent(entity);
            count = getSqlMapper().updateByPrimaryKeySelective(entity);
        }
        return count;
    }

    private void setDAparent(BaseCustom entity) {
        CustomTypeEnum customTypeEnum = ValueEnumUtils.getEnum(CustomTypeEnum.class, entity.getCustomType());
        switch (customTypeEnum){
            case DA:
                entity.setParentId(entity.getId());
                break;
            case FA:
                entity.setParentId(0);
                break;
        }
    }
    /**
     * 设置直客的广告主为自己
     *
     * @param entity
     */
    private void updateDAparent(BaseCustom entity) {
        setDAparent(entity);
        getSqlMapper().updateByPrimaryKeySelective(entity);
    }

}
