package cn.advu.workflow.repo.base;


import cn.advu.workflow.domain.base.IEntity;

public interface IRepo<T extends IEntity> {
    int add(T entity);
    int addSelective(T entity);
    int update(T entity);
    int updateSelective(T entity);
    int remove(Integer id);
    T findOne(Integer id);
    int logicRemove(T entity);
}
