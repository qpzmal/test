package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.repo.fcf_vu.BaseAreaFinanceRepo;
import cn.advu.workflow.repo.fcf_vu.BaseAreaRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.AreaFinanceMananger;
import cn.advu.workflow.web.manager.DateManager;
import cn.advu.workflow.web.manager.TreeMananger;
import cn.advu.workflow.web.service.base.AreaFinanceService;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class AreaFinanceServiceImpl implements AreaFinanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AreaFinanceServiceImpl.class);

    @Autowired
    BaseAreaFinanceRepo baseAreaFinanceRepo;

    @Autowired
    DateManager dateManager;

    @Autowired
    AreaFinanceMananger areaFinanceMananger;

    @Override
    public ResultJson<List<BaseAreaFinance>> findByArea(Integer areaId) {
        ResultJson<List<BaseAreaFinance>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        List<BaseAreaFinance> areaFinanceList = baseAreaFinanceRepo.findByArea(areaId);
        result.setData(areaFinanceList);
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseAreaFinance baseAreaFinance) {

        // 开始日期、结束日期不能为空，开始日期<结束日期
        Date startDate = baseAreaFinance.getStartDate();
        Date endDate = baseAreaFinance.getEndDate();
        dateManager.validateStartEndDate(startDate, endDate);
        // 所属区域IS NOT NULL
        Integer areaId = baseAreaFinance.getAreaId();
        AssertUtil.assertNotNull(areaId);
        // 结算日期重复
        if (areaFinanceMananger.isDuplicated(areaId, startDate, endDate)) {
            throw new ServiceException(MessageConstants.DATE_IS_DUPLICATED);
        }
        if (areaFinanceMananger.isNameDuplicated(null, baseAreaFinance.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        ResultJson<Integer> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        baseAreaFinanceRepo.addSelective(baseAreaFinance);
        result.setData(baseAreaFinance.getId());
        return result;
    }

    @Override
    public ResultJson<Void> update(BaseAreaFinance baseAreaFinance) {

        Integer id = baseAreaFinance.getId();
        AssertUtil.assertNotNull(id);

        // 开始日期、结束日期不能为空，开始日期<结束日期
        Date startDate = baseAreaFinance.getStartDate();
        Date endDate = baseAreaFinance.getEndDate();
        dateManager.validateStartEndDate(startDate, endDate);
        // 所属区域IS NOT NULL
        Integer areaId = baseAreaFinance.getAreaId();
        AssertUtil.assertNotNull(areaId);
        // 结算日期重复
        if (areaFinanceMananger.isDuplicated(id, areaId, startDate, endDate)) {
            throw new ServiceException(MessageConstants.DATE_IS_DUPLICATED);
        }
        if (areaFinanceMananger.isNameDuplicated(baseAreaFinance.getId(), baseAreaFinance.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }

        ResultJson<Void> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        baseAreaFinanceRepo.updateSelective(baseAreaFinance);
        return result;
    }

    @Override
    public ResultJson<BaseAreaFinance> findById(Integer id) {
        ResultJson<BaseAreaFinance> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        result.setData(baseAreaFinanceRepo.findOne(id));
        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {
        AssertUtil.assertNotNull(id);

        BaseAreaFinance oldBaseAreaFinance = baseAreaFinanceRepo.findOne(id);
        baseAreaFinanceRepo.logicRemove(oldBaseAreaFinance);

        return new ResultJson(WebConstants.OPERATION_SUCCESS);
    }
}
