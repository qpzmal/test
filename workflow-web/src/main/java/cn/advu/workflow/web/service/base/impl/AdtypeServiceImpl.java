package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.enums.LogTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.repo.fcf_vu.BaseAdtypeRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.AdtypeMananger;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.service.base.AdtypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class AdtypeServiceImpl implements AdtypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdtypeServiceImpl.class);

    @Autowired
    private BaseAdtypeRepo baseAdtypeRepo;
    @Autowired
    AdtypeMananger adtypeMananger;

    @Autowired
    BizLogManager bizLogManager;

    @Override
    public ResultJson<Integer> addAdtype(BaseAdtype baseAdtype) {

        // validator
        if (adtypeMananger.isNameDuplicated(null, baseAdtype.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        // add
        Integer insertCount = baseAdtypeRepo.addSelective(baseAdtype);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建媒体失败!");
        }

        // log
        bizLogManager.addBizLog(baseAdtypeRepo.findOne(baseAdtype.getId()), "广告类型管理/添加广告类型", Integer.valueOf(LogTypeEnum.ADD.getValue()));

        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<BaseAdtype>> findAll() {
        ResultJson<List<BaseAdtype>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseAdtypeRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<BaseAdtype> findById(Integer id) {
        ResultJson<BaseAdtype> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseAdtypeRepo.findOne(id));
        return result;
    }

    @Override
    public ResultJson<Integer> udpateAdtype(BaseAdtype baseAdtype) {
        Integer id = baseAdtype.getId();
        if (id == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }

        if (adtypeMananger.isNameDuplicated(id, baseAdtype.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        // log
        bizLogManager.addBizLog(baseAdtypeRepo.findOne(id), "广告类型管理/更新广告类型", Integer.valueOf(LogTypeEnum.UPDATE.getValue()));

        Integer updateCount = baseAdtypeRepo.updateSelective(baseAdtype);
        if(updateCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新广告类型失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Void> remove(Integer id) {
        BaseAdtype baseAdtype = baseAdtypeRepo.findOne(id);
        if (baseAdtype != null) {
            baseAdtype.setDelFlag("1");
            baseAdtypeRepo.update(baseAdtype);
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }
}
