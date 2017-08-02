package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.base.AbstractTreeEntity;
import cn.advu.workflow.domain.enums.LogTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.domain.fcf_vu.DeptVO;
import cn.advu.workflow.repo.base.IRepo;
import cn.advu.workflow.repo.fcf_vu.BaseDeptRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.*;
import cn.advu.workflow.web.service.AbstractTreeService;
import cn.advu.workflow.web.service.base.DeptService;
import cn.advu.workflow.web.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class DeptServiceImpl extends AbstractTreeService implements DeptService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Autowired
    private BaseDeptRepo baseDeptRepo;
    @Autowired
    private UserMananger userMananger;
    @Autowired
    private PersonMananger personMananger;

    @Autowired
    DeptMananger deptMananger;

    @Autowired
    BizLogManager bizLogManager;

    @Override
    protected IRepo<? extends AbstractTreeEntity> getRepo() {
        return baseDeptRepo;
    }

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
    public ResultJson<List<BaseDept>> findChildDept(Integer areaId, Integer parentId) {
        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        if (areaId == null || parentId == null) {
            resultJson.setCode(WebConstants.OPERATION_FAILURE);
            return resultJson;
        }
        resultJson.setData(baseDeptRepo.findChildDept(areaId, parentId));
        return resultJson;
    }

    @Override
    public ResultJson<List<DeptVO>> findChildDeptWithName(Integer areaId, Integer parentId) {
        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(baseDeptRepo.findChildDeptWithAreaNameAndParentName(areaId, parentId));
        return resultJson;
    }

    @Override
    public ResultJson<Integer> add(BaseDept baseDept) {

        AssertUtil.assertNotNull(baseDept);
        AssertUtil.assertNotNull(baseDept.getAreaId(), MessageConstants.ASSIGN_AREA_IS_NULL);
        // 名称不能重复
        if (deptMananger.isNameDuplicated(null, baseDept.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        // 设置level
        this.resetLevel(baseDept);

        ResultJson<Integer> resultJson = new ResultJson();
        Integer deptId = baseDeptRepo.addSelective(baseDept);
        resultJson.setData(deptId);

        // log
        bizLogManager.addBizLog(baseDeptRepo.findOne(baseDept.getId()), "部门管理/添加部门", Integer.valueOf(LogTypeEnum.ADD.getValue()));

        return resultJson;
    }

    @Override
    public ResultJson<Void> update(BaseDept baseDept) {
        AssertUtil.assertNotNull(baseDept);
        AssertUtil.assertNotNull(baseDept.getId());
        // 名称不能重复
        if (deptMananger.isNameDuplicated(baseDept.getId(), baseDept.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        Integer parentId = baseDept.getParentId();
        if (parentId != null && parentId.equals(baseDept.getId())) {
            throw new ServiceException(MessageConstants.PARENT_IS_SELF);
        }

        // 设置level
        this.resetLevel(baseDept);

        // log
        bizLogManager.addBizLog(baseDeptRepo.findOne(baseDept.getId()), "部门管理/更新部门", Integer.valueOf(LogTypeEnum.UPDATE.getValue()));

        ResultJson<Void> resultJson = new ResultJson();
        baseDeptRepo.updateSelective(baseDept);

        return resultJson;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {

        List<BaseDept> childDeptList =  baseDeptRepo.findChildDept(null, id);
        if (childDeptList != null && !childDeptList.isEmpty()) {
            throw new ServiceException(MessageConstants.DEPT_IS_USING);
        }
        if (userMananger.hasUserUnderDept(id)) {
            throw new ServiceException(MessageConstants.DEPT_IS_USING);
        }
        if (personMananger.hasPersonUnderDept(id)) {
            throw new ServiceException(MessageConstants.DEPT_IS_USING);
        }

        BaseDept baseDept = baseDeptRepo.findOne(id);

        // log
        bizLogManager.addBizLog(baseDept, "部门管理/删除部门", Integer.valueOf(LogTypeEnum.DELETE.getValue()));

        AssertUtil.assertNotNull(baseDept, MessageConstants.DEPT_IS_NOT_EXISTS);
        baseDeptRepo.logicRemove(baseDept);

        return new ResultJson<>();
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
        Collections.sort(sortLevelList, new LevelComparetor());

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
