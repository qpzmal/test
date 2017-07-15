package cn.advu.workflow.web.service;

import cn.advu.workflow.domain.base.AbstractTreeEntity;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.repo.base.IRepo;
import cn.advu.workflow.web.util.AssertUtil;
import org.apache.poi.ss.formula.functions.T;

/**
 * Created by wangry on 17/7/15.
 */
public abstract class AbstractTreeService {

    protected abstract  IRepo<? extends AbstractTreeEntity> getRepo();

    public <T extends AbstractTreeEntity> void resetLevel(T baseArea) {

        Integer level = findLevel(baseArea.getId(), baseArea.getParentId());
        if (level != null) {
            baseArea.setLevel(level);
        }

    }

    /**
     * 返回子节点的level
     * @param parentId
     * @return
     */
    private  Integer findCurrentLevel(Integer parentId) {

        Integer level = 1;
        // 无父级节点
        if (parentId == null || parentId.intValue() == 0) {
            return level;
        }

        AbstractTreeEntity currentParentArea = getRepo().findOne(parentId);
        AssertUtil.assertNotNull(currentParentArea);
        Integer currentParentLevel = currentParentArea.getLevel();
        AssertUtil.assertNotNull(currentParentLevel);

        level = currentParentLevel + 1;

        return level;
    }

    /**
     * 更新子节点的level
     *
     * @param id
     * @param parentId
     * @return
     */
    public Integer findLevel(Integer id, Integer parentId) {
        if (id == null) {
            return findCurrentLevel(parentId);
        } else {
            return findCurrentLevel(id, parentId);
        }
    }

    /**
     * 更新子节点的level
     *
     * @param id
     * @param parentId
     * @return
     */
    public Integer findCurrentLevel(Integer id, Integer parentId) {

        Integer level = null;

        AbstractTreeEntity oldBaseArea = getRepo().findOne(parentId);
        AssertUtil.assertNotNull(oldBaseArea);
        Integer oldParentId = oldBaseArea.getParentId();

        if (parentId == null) {
            parentId = 0;
        }

        // 父级节点变更
        if (!oldParentId.equals(parentId)) {

            // 变更后无父级节点
            if (parentId == 0) {
                level = 1;
            } else {
                AbstractTreeEntity currentParentArea = getRepo().findOne(parentId);
                AssertUtil.assertNotNull(currentParentArea);
                Integer currentLevel = currentParentArea.getLevel();
                AssertUtil.assertNotNull(currentLevel);

                Integer oldLevel = oldBaseArea.getLevel();
                // 当前area的层级变化
                if (!oldLevel.equals(currentLevel)) {
                    level = currentLevel + 1;
                }
            }

        }

        return level;

    }
}
