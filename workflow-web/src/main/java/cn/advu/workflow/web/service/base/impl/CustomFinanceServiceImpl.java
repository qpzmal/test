package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.repo.fcf_vu.BaseAreaFinanceRepo;
import cn.advu.workflow.repo.fcf_vu.BaseCustomFinanceRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.AreaFinanceService;
import cn.advu.workflow.web.service.base.CustomFinanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class CustomFinanceServiceImpl implements CustomFinanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFinanceServiceImpl.class);

    @Autowired
    BaseCustomFinanceRepo baseCustomFinanceRepo;

    @Override
    public ResultJson<List<BaseCustomFinance>> findByCustom(Integer customId) {
        ResultJson<List<BaseCustomFinance>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        List<BaseCustomFinance> customFinanceList = baseCustomFinanceRepo.findByCustom(customId);
        result.setData(customFinanceList);
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseCustomFinance baseCustomFinance) {
        ResultJson<Integer> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        baseCustomFinanceRepo.addSelective(baseCustomFinance);
        result.setData(baseCustomFinance.getId());
        return result;
    }

    @Override
    public ResultJson<Void> update(BaseCustomFinance baseCustomFinance) {
        ResultJson<Void> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        baseCustomFinanceRepo.updateSelective(baseCustomFinance);
        return result;
    }

    @Override
    public ResultJson<BaseCustomFinance> findById(Integer id) {
        ResultJson<BaseCustomFinance> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        result.setData(baseCustomFinanceRepo.findOne(id));
        return result;
    }
}
