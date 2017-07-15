package cn.advu.workflow.repo.base.impl;



import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.base.BaseTreeDAO;
import cn.advu.workflow.domain.base.AbstractTreeEntity;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public abstract class AbstractTreeRepo<T extends AbstractTreeEntity> extends AbstractRepo<T> {

    protected abstract BaseTreeDAO<T> getTreeSqlMapper();

    @Override
    protected BaseDAO<T> getSqlMapper() {
        return getTreeSqlMapper();
    }

    @Override
    public int addSelective(T entity) {
        int count = 0;
        if (entity != null) {
            resetLevel(entity);
            count = super.addSelective(entity);
        }

        return count;
    }


    @Override
    public int add(T entity) {
        int count = 0;
        if (entity != null) {
            resetLevel(entity);
            count = super.add(entity);
        }
        return count;
    }

    @Override
    public int updateSelective(T entity) {
        int count = 0;
        if (entity != null) {
            count = updateLevel(entity);
        }
        return count;
    }

    private Integer updateLevel(T entity) {

        Integer count = 0;

        // 判定LEVEL 是否有变化
        Integer id = entity.getId();
        T oldEntity = getSqlMapper().selectByPrimaryKey(id);
        Integer oldLevel = oldEntity.getLevel();
        Integer level = entity.getLevel();
        if ((level == null) || (level != null && level.equals(oldLevel))) {
            count = getTreeSqlMapper().updateByPrimaryKeySelective(entity);
            return count;
        }

        count = getTreeSqlMapper().updateByPrimaryKeySelective(entity);
        // 查询出所有子节点
        List<T> childEntities = getTreeSqlMapper().queryChildList(id);
        if (childEntities == null || childEntities.isEmpty()) {
            count = getTreeSqlMapper().updateByPrimaryKeySelective(entity);
            return count;
        }

        Integer childLevel = level + 1;
        for (T childEntity : childEntities) {
            childEntity.setLevel(childLevel);
            count = count + updateLevel(childEntity);
        }
        return count;
    }

    /**
     * 重置level
     *
     * @param entity
     */
    private void resetLevel(T entity) {
        Integer parentId = entity.getParentId();
        if (parentId == null || parentId.intValue() == 0) {
            entity.setParentId(0);
            entity.setLevel(1);
        }
    }

}
