package cn.advu.workflow.repo.base.impl;


import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.base.IEntity;
import cn.advu.workflow.repo.base.IRepo;

/**
 * 抽象仓库
 *
 * @param <T>
 */
public abstract class AbstractRepo<T extends IEntity> implements IRepo<T> {

    protected abstract BaseDAO<T> getSqlMapper();

    @Override
    public int add(T entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().insert(entity);
        }

        return count;
    }

    @Override
    public int addSelective(T entity) {
        int count = 0;
        if (entity != null) {
            count = getSqlMapper().insertSelective(entity);
        }
        return count;
    }
}
