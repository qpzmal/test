package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.repo.fcf_vu.BaseAreaFinanceRepo;
import cn.advu.workflow.repo.fcf_vu.BaseCustomFinanceRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.CustomFinanceMananger;
import cn.advu.workflow.web.service.base.AreaFinanceService;
import cn.advu.workflow.web.service.base.CustomFinanceService;
import cn.advu.workflow.web.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class CustomFinanceServiceImpl implements CustomFinanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFinanceServiceImpl.class);

    @Autowired
    BaseCustomFinanceRepo baseCustomFinanceRepo;
    @Autowired
    CustomFinanceMananger customFinanceMananger;

    @Override
    public ResultJson<List<BaseCustomFinance>> findByCustom(Integer customId) {
        ResultJson<List<BaseCustomFinance>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        List<BaseCustomFinance> customFinanceList = baseCustomFinanceRepo.findByCustom(customId);
        result.setData(customFinanceList);
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseCustomFinance baseCustomFinance) {

        // 客户ID不能为空
        Date startDate = baseCustomFinance.getStartDate();
        Date endDate = baseCustomFinance.getEndDate();

        AssertUtil.assertNotNull(baseCustomFinance.getName(), MessageConstants.NAME_IS_NULL);
        AssertUtil.assertNotNull(baseCustomFinance.getCustomId(), MessageConstants.CUSTOM_IS_NULL);
        AssertUtil.assertNotNull(startDate, MessageConstants.START_DATE_IS_NULL);
        AssertUtil.assertNotNull(endDate, MessageConstants.END_DATE_IS_NULL);
        if (startDate.after(endDate)) {
            throw new ServiceException(MessageConstants.CUSTOM_DATE_IS_DUPLICATED);
        }
        if (customFinanceMananger.isDuplicated(baseCustomFinance.getCustomId(), startDate, endDate)) {
            throw new ServiceException(MessageConstants.CUSTOM_DATE_IS_DUPLICATED);
        }

        ResultJson<Integer> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        baseCustomFinanceRepo.add(baseCustomFinance);
        result.setData(baseCustomFinance.getId());
        return result;
    }

    @Override
    public ResultJson<Void> update(BaseCustomFinance baseCustomFinance) {
        // 客户ID不能为空
        Date startDate = baseCustomFinance.getStartDate();
        Date endDate = baseCustomFinance.getEndDate();
        AssertUtil.assertNotNull(baseCustomFinance.getName(), MessageConstants.NAME_IS_NULL);
        AssertUtil.assertNotNull(baseCustomFinance.getCustomId(), MessageConstants.CUSTOM_IS_NULL);
        AssertUtil.assertNotNull(startDate, MessageConstants.START_DATE_IS_NULL);
        AssertUtil.assertNotNull(endDate, MessageConstants.END_DATE_IS_NULL);
        if (startDate.after(endDate)) {
            throw new ServiceException(MessageConstants.CUSTOM_DATE_IS_DUPLICATED);
        }
        if (customFinanceMananger.isDuplicated(baseCustomFinance.getCustomId(), startDate, endDate)) {
            throw new ServiceException(MessageConstants.CUSTOM_DATE_IS_DUPLICATED);
        }

        ResultJson<Void> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        baseCustomFinanceRepo.update(baseCustomFinance);
        return result;
    }

    @Override
    public ResultJson<BaseCustomFinance> findById(Integer id) {
        ResultJson<BaseCustomFinance> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        result.setData(baseCustomFinanceRepo.findOne(id));
        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {
        AssertUtil.assertNotNull(id);
        // 4A -> 直客，广告主
        BaseCustomFinance oldBaseCustomFinance = baseCustomFinanceRepo.findOne(id);
        baseCustomFinanceRepo.logicRemove(oldBaseCustomFinance);

        return new ResultJson(WebConstants.OPERATION_SUCCESS);
    }
}
