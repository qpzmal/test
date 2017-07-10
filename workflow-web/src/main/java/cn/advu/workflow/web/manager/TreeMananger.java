package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.base.AbstractTreeEntity;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.web.dto.TreeNode;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by wangry on 17/7/9.
 */
@Component
public class TreeMananger {

    public <T extends AbstractTreeEntity> Collection<TreeNode> buildDeptTreeNodeList(List<T> deptList) {

        // 入参转成key=level的map
        Map<Integer, Map<Integer, T>> LevelMap = convertToLevelMap(deptList);

        Map<Integer, TreeNode> treeNodeMap = new HashMap<>();

        // 按照level倒序排序
        List<Integer> sortLevelList = new LinkedList<>();
        sortLevelList.addAll(LevelMap.keySet());
        Collections.sort(sortLevelList, new LevelComparetor());

        for (Integer levelTemp : sortLevelList) {

            // 层级>1时，才需要获取父类节点
            Map<Integer, T> parentMap = null;
            Integer parentLevel = levelTemp - 1;
            if (parentLevel > 0) {
                parentMap = LevelMap.get(parentLevel);
            }

            // 当前level列表
            Collection<T> levelDeptCol = LevelMap.get(levelTemp).values();
            for (T entity : levelDeptCol) {

                // 创建当前节点
                Integer id = entity.getId();
                TreeNode treeNode = treeNodeMap.get(id);
                if (treeNode == null) {
                    treeNode = new TreeNode();
                    treeNodeMap.put(id, treeNode);
                }
                treeNode.setText(entity.getName());
                treeNode.setValue(entity.getId());

                if (parentLevel > 0) {
                    // 移除当前节点
                    treeNodeMap.remove(id);

                    // 设置父节点的子节点列表
                    Integer parentId = entity.getParentId();
                    TreeNode parentNode = treeNodeMap.get(parentId);
                    if (parentNode == null) {
                        parentNode = new TreeNode();
                        treeNodeMap.put(parentId, parentNode);
                    }
                    List<TreeNode> childNodeList = parentNode.getNodes();
                    if (childNodeList == null) {
                        childNodeList = new LinkedList<>();
                        parentNode.setNodes(childNodeList);
                    }
                    childNodeList.add(treeNode);
                }
            }
        }
        return treeNodeMap.values();
    }

    /**
     *
     * 列表转换成层级map
     *
     * @param entityList
     * @return
     */
    private <T extends AbstractTreeEntity> Map<Integer, Map<Integer, T>> convertToLevelMap(List<T> entityList) {

        Map<Integer, Map<Integer, T>> LevelMap = new HashMap<>();

        for (T treeEntity : entityList) {

            Integer level = treeEntity.getLevel();

            Map<Integer, T> entityMap = LevelMap.get(level);
            if (entityMap == null) {
                entityMap = new HashMap<>();
                LevelMap.put(level, entityMap);
            }
            entityMap.put(treeEntity.getId(), treeEntity);
        }
        return LevelMap;
    }


    public String converToTreeJsonStr(Collection<TreeNode> parentNodes) {
        String parentTreeJson = null;
        if (parentNodes != null && !parentNodes.isEmpty()) {
            parentTreeJson = JSONObject.toJSONString(parentNodes);
        }
        return parentTreeJson;
    }
}
