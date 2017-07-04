package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.repo.fcf_vu.BaseDeptRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.service.DeptLevelComparetor;
import cn.advu.workflow.web.service.base.DeptService;
import cn.advu.workflow.web.service.base.IndustryService;
import net.sf.ehcache.search.parser.MCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class DeptServiceImpl implements DeptService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private BaseDeptRepo baseDeptRepo;


    @Override
    public ResultJson<List<BaseDept>> findAll() {
        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(baseDeptRepo.findAll());
        return resultJson;
    }

    @Override
    public ResultJson<List<BaseDept>> findAreaDept(Integer areaId) {
        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        if (areaId == null) {
            resultJson.setCode(WebConstants.OPERATION_FAILURE);
            return resultJson;
        }

        resultJson.setData(baseDeptRepo.findAreaDept(areaId));

        return resultJson;
    }

    @Override
    public ResultJson<Integer> add(BaseDept baseDept) {
        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        // TODO 校验areaId 和 parentId是否一致
        Integer parentId = baseDept.getParentId();
        if (parentId == null) {
            baseDept.setParentId(-1);
            baseDept.setLevel(1);
        } else {
            BaseDept parentDept = baseDeptRepo.findOne(parentId);
            if (parentDept == null) {
                resultJson.setCode(WebConstants.OPERATION_FAILURE);
                return resultJson;
            }
            Integer parentLevel = parentDept.getLevel();
            baseDept.setLevel(parentLevel +1);
        }

        Integer deptId = baseDeptRepo.addSelective(baseDept);
        resultJson.setData(deptId);

        return resultJson;
    }

    @Override
    public ResultJson<BaseDept> findById(Integer id) {
        ResultJson<BaseDept> resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(baseDeptRepo.findOne(id));
        return resultJson;
    }

    @Override
    public ResultJson<Collection<TreeNode>> findAreaDeptNodeList(Integer areaId) {

        ResultJson<Collection<TreeNode>> resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);

        List<BaseDept> deptList = baseDeptRepo.findAreaDept(areaId);

        Collection<TreeNode> treeNodes = buildDeptTreeNodeList(deptList);

        resultJson.setData(treeNodes);

        return resultJson;
    }

    @Override
    public ResultJson<Collection<TreeNode>> findDeptNodeList(List<BaseDept> deptList) {
        ResultJson reuslt = new ResultJson(WebConstants.OPERATION_SUCCESS);
        reuslt.setData(buildDeptTreeNodeList(deptList));
        return reuslt;
    }


    /**
     *
     * 部门列表转换成层级map
     *
     * @param deptList
     * @return
     */
    private Map<Integer, Map<Integer, BaseDept>> convertToLevelDeptMap(List<BaseDept> deptList) {

        Map<Integer, Map<Integer, BaseDept>> LevelDeptMap = new HashMap<>();

        for (BaseDept baseDeptTemp:deptList) {

            Integer level = baseDeptTemp.getLevel();

            Map<Integer, BaseDept> deptMap = LevelDeptMap.get(level);
            if (deptMap == null) {
                deptMap = new HashMap<>();
                LevelDeptMap.put(level, deptMap);
            }

            Integer deptId = baseDeptTemp.getId();
            deptMap.put(deptId, baseDeptTemp);
        }
        return LevelDeptMap;
    }



    private Collection<TreeNode> buildDeptTreeNodeList(List<BaseDept> deptList) {

        Map<Integer, Map<Integer, BaseDept>> LevelDeptMap = convertToLevelDeptMap(deptList);

        Map<Integer, TreeNode> treeNodeMap = new HashMap<>();

        // TODO 按照level倒序排序
        SortedMap<Integer, Map<Integer, BaseDept>> sortDeptByLevel =new TreeMap<>(LevelDeptMap);

        List<Integer> sortLevelList = new LinkedList<>();
        sortLevelList.addAll(sortDeptByLevel.keySet());
        Collections.sort(sortLevelList, new DeptLevelComparetor());

        for (Integer levelTemp : sortLevelList) {

            // 层级>1时，才需要获取父类节点
            Map<Integer, BaseDept> parentDeptMap = null;
            Integer parentLevel = levelTemp - 1;
            if (parentLevel > 0) {
                parentDeptMap = sortDeptByLevel.get(parentLevel);
            }

            // 当前level的部门列表
            Collection<BaseDept> levelDeptCol = sortDeptByLevel.get(levelTemp).values();
            for (BaseDept levelDeptTemp : levelDeptCol) {

                // 创建当前节点
                Integer id = levelDeptTemp.getId();
                TreeNode treeNode = treeNodeMap.get(id);
                if (treeNode == null) {
                    treeNode = new TreeNode();
                    treeNodeMap.put(id, treeNode);
                }
                treeNode.setText(levelDeptTemp.getName());
                treeNode.setValue(levelDeptTemp.getId());

                if (parentLevel > 0) {
                    treeNodeMap.remove(id);
                    // 设置父节点的子节点列表
                    Integer parentId = levelDeptTemp.getParentId();
                    TreeNode parentNode = treeNodeMap.get(levelDeptTemp.getParentId());
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

}
