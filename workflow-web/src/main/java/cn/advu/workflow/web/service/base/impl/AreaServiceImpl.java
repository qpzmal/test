package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.enums.LogTypeEnum;
import cn.advu.workflow.domain.fcf_vu.AreaVO;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.repo.fcf_vu.BaseAreaRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.AreaManager;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.manager.TreeMananger;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class AreaServiceImpl implements AreaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private BaseAreaRepo baseAreaRepo;

    @Autowired
    private AreaManager areaManager;

    @Autowired
    private TreeMananger treeMananger;

    @Autowired
    BizLogManager bizLogManager;

    @Override
    public ResultJson<List<BaseArea>> findAll() {
        ResultJson<List<BaseArea>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseAreaRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> addArea(BaseArea baseArea) {
        // 区域编号是否重复
        if (areaManager.isCodeDuplicated(null, baseArea.getCode())) {
            throw new ServiceException(MessageConstants.CODE_IS_DUPLICATED);
        }
        if (areaManager.isNameDuplicated(null, baseArea.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }

        areaManager.resetLevel(baseArea);
        Integer insertCount = baseAreaRepo.addSelective(baseArea);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建公司失败!");
        }

        // log
        bizLogManager.addBizLog(baseAreaRepo.findOne(baseArea.getId()), "地域管理/添加地域", Integer.valueOf(LogTypeEnum.ADD.getValue()));

        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> updateArea(BaseArea baseArea) {

        if (baseArea.getId() == null) {
        return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        // 区域编号是否重复
        if (areaManager.isCodeDuplicated(baseArea.getId(), baseArea.getCode())) {
            throw new ServiceException(MessageConstants.CODE_IS_DUPLICATED);
        }
        if (areaManager.isNameDuplicated(baseArea.getId(), baseArea.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        areaManager.resetLevel(baseArea);
        // log
        bizLogManager.addBizLog(baseAreaRepo.findOne(baseArea.getId()), "地域管理/更新地域", Integer.valueOf(LogTypeEnum.UPDATE.getValue()));

        Integer insertCount = baseAreaRepo.updateSelective(baseArea);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新公司失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseArea> findById(Integer id) {
        ResultJson<BaseArea> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseAreaRepo.findOne(id));
        return result;
    }

    @Override
    public ResultJson<Collection<TreeNode>> findAreaNodeList(Integer areaId) {

        ResultJson<Collection<TreeNode>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);

        List<BaseArea> areaList = baseAreaRepo.findByParent(areaId);
        Collection<TreeNode> treeNodes = treeMananger.buildDeptTreeNodeList(areaList);
        result.setData(treeNodes);

        return result;
    }

    @Override
    public ResultJson<List<AreaVO>> findByParent(Integer parentId) {

        ResultJson<List<AreaVO>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        List<AreaVO> areaVOList = baseAreaRepo.findByParentAreaVO(parentId);
        result.setData(areaVOList);
        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {
        AssertUtil.assertNotNull(id);

        BaseArea oldBaseCustom = baseAreaRepo.findOne(id);
        AssertUtil.assertNotNull(oldBaseCustom, "区域"+MessageConstants.AREA_IS_NOT_EXISTS);

        if (areaManager.hasSubordinate(id)) {
            throw new ServiceException(MessageConstants.AREA_HAS_SUBORDINATE);
        }
        // log
        bizLogManager.addBizLog(oldBaseCustom, "地域管理/删除地域", Integer.valueOf(LogTypeEnum.DELETE.getValue()));

        int updateCount = baseAreaRepo.logicRemove(oldBaseCustom);
        if(updateCount != 1) {
            throw new ServiceException(MessageConstants.AREA_IS_NOT_EXISTS);
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }
}
