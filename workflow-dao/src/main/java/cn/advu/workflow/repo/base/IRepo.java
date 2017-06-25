package cn.advu.workflow.repo.base;


import cn.advu.workflow.domain.base.IEntity;

public interface IRepo<T extends IEntity> {
    int add(T entity);
    int addSelective(T entity);
}
