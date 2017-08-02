package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.enums.CustomTypeEnum;
import cn.advu.workflow.domain.enums.LogTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BasePerson;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.repo.fcf_vu.BaseRegionRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.manager.RegionManager;
import cn.advu.workflow.web.service.base.RegionService;
import cn.advu.workflow.web.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class RegionServiceImpl implements RegionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);

    @Autowired
    private BaseRegionRepo baseRegionRepo;
    @Autowired
    RegionManager regionManager;

    @Autowired
    BizLogManager bizLogManager;

    @Override
    public ResultJson<Integer> addRegion(BaseRegion baseRegion) {

        AssertUtil.assertNotNull(baseRegion.getType(), MessageConstants.REGION_TYPE_IS_NULL);
        if (regionManager.isNameDuplicated(null, baseRegion.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }

        Integer insertCount = baseRegionRepo.addSelective(baseRegion);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建地域失败!");
        }
        // log
        bizLogManager.addBizLog(baseRegionRepo.findOne(baseRegion.getId()), "地域管理/添加地域", Integer.valueOf(LogTypeEnum.ADD.getValue()));
        return new ResultJson<>();
    }

    @Override
    public ResultJson<Integer> updateRegion(BaseRegion baseRegion) {

        AssertUtil.assertNotNull(baseRegion.getType(), MessageConstants.REGION_TYPE_IS_NULL);
        if (regionManager.isNameDuplicated(baseRegion.getId(), baseRegion.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }

        BaseRegion oldRegion = baseRegionRepo.findOne(baseRegion.getId());

        Integer insertCount = baseRegionRepo.updateSelective(baseRegion);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新地域失败!");
        }
        // log
        bizLogManager.addBizLog(oldRegion, "地域管理/更新地域", Integer.valueOf(LogTypeEnum.UPDATE.getValue()));

        return new ResultJson<>();
    }

    @Override
    public ResultJson<List<BaseRegion>> findAll() {
        ResultJson<List<BaseRegion>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseRegionRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<BaseRegion> findById(Integer id) {
        ResultJson<BaseRegion> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseRegionRepo.findOne(id));
        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {
        AssertUtil.assertNotNull(id);

        BaseRegion oldBaseRegion = baseRegionRepo.findOne(id);
        AssertUtil.assertNotNull(oldBaseRegion, MessageConstants.REGION_IS_NOT_EXISTS);

        // log
        bizLogManager.addBizLog(oldBaseRegion, "地域管理/删除地域", Integer.valueOf(LogTypeEnum.DELETE.getValue()));

        int updateCount = baseRegionRepo.logicRemove(oldBaseRegion);
        if(updateCount != 1) {
            throw new ServiceException(MessageConstants.REGION_IS_NOT_EXISTS);
        }


        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }
}
