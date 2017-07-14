package cn.advu.workflow.repo.base.impl;



import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.base.BaseTreeDAO;
import cn.advu.workflow.domain.base.AbstractTreeEntity;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import org.springframework.stereotype.Repository;



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
            count = getSqlMapper().updateByPrimaryKeySelective(entity);
            Integer level = entity.getLevel();
            if (level != null) {
                Integer childLevel = level + 1;
                getTreeSqlMapper().updateChildLevel(entity.getId(), childLevel);
            }
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
