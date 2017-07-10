package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.repo.fcf_vu.BaseAreaFinanceRepo;
import cn.advu.workflow.repo.fcf_vu.BaseAreaRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.manager.TreeMananger;
import cn.advu.workflow.web.service.base.AreaFinanceService;
import cn.advu.workflow.web.service.base.AreaService;
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
public class AreaFinanceServiceImpl implements AreaFinanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AreaFinanceServiceImpl.class);

    @Autowired
    BaseAreaFinanceRepo baseAreaFinanceRepo;

    @Override
    public ResultJson<List<BaseAreaFinance>> findByArea(Integer areaId) {
        ResultJson<List<BaseAreaFinance>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        List<BaseAreaFinance> areaFinanceList = baseAreaFinanceRepo.findByArea(areaId);
        result.setData(areaFinanceList);
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseAreaFinance baseAreaFinance) {
        ResultJson<Integer> result = new ResultJson(WebConstants.OPERATION_SUCCESS);
        baseAreaFinanceRepo.addSelective(baseAreaFinance);
        result.setData(baseAreaFinance.getId());
        return result;
    }
}
