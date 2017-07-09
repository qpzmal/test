package cn.advu.workflow.repo.base.impl;



import cn.advu.workflow.domain.base.AbstractTreeEntity;
import org.springframework.stereotype.Repository;



@Repository
public abstract class AbstractTreeRepo<T extends AbstractTreeEntity> extends AbstractRepo<T> {


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

    /**
     * 重置level
     *
     * @param entity
     */
    private void resetLevel(T entity) {
        Integer parentId = entity.getParentId();
        if (parentId == null || parentId.intValue() == 0) {
            entity.setParentId(0);
            entity.setLevel(-1);
        }
    }
}
