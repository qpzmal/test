package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.enums.LogTypeEnum;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.repo.fcf_vu.BaseIndustryRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.manager.IndustryManager;
import cn.advu.workflow.web.service.base.IndustryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class IndustryServiceImpl implements IndustryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndustryServiceImpl.class);

    @Autowired
    private BaseIndustryRepo baseIndustryRepo;

    @Autowired
    IndustryManager industryManager;

    @Autowired
    BizLogManager bizLogManager;

    @Override
    public ResultJson<Integer> addIndustry(BaseIndustry baseIndustry) {
        if (industryManager.isNameDuplicated(null, baseIndustry.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        Integer insertCount = baseIndustryRepo.addSelective(baseIndustry);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建客户行业失败!");
        }
        // log
        bizLogManager.addBizLog(baseIndustryRepo.findOne(baseIndustry.getId()), "客户行业管理/添加客户行业", Integer.valueOf(LogTypeEnum.ADD.getValue()));

        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> updateIndustry(BaseIndustry baseIndustry) {
        if (baseIndustry.getId() == null) {
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "客户行业ID没有设置!");
        }
        if (industryManager.isNameDuplicated(baseIndustry.getId(), baseIndustry.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        BaseIndustry oldBaseIndustry = baseIndustryRepo.findOne(baseIndustry.getId());

        Integer updateCount = baseIndustryRepo.updateSelective(baseIndustry);
        if(updateCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新客户行业失败!");
        }

        // log
        bizLogManager.addBizLog(oldBaseIndustry, "客户行业管理/更新客户行业", Integer.valueOf(LogTypeEnum.UPDATE.getValue()));

        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<BaseIndustry>> findAll() {
        ResultJson<List<BaseIndustry>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseIndustryRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<BaseIndustry> findById(Integer id) {
        ResultJson<BaseIndustry> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseIndustryRepo.findOne(id));
        return result;
    }
}
