package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.repo.fcf_vu.BaseFinancialindexRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaTypeRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.FinancialindexService;
import cn.advu.workflow.web.service.base.MediaTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class FinancialindexServiceImpl implements FinancialindexService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FinancialindexServiceImpl.class);

    @Autowired
    private BaseFinancialindexRepo baseFinancialindexRepo;

    @Override
    public ResultJson<List<BaseFinancialindex>> findAll() {
        ResultJson<List<BaseFinancialindex>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseFinancialindexRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseFinancialindex baseFinancialindex) {
        Integer insertCount = baseFinancialindexRepo.addSelective(baseFinancialindex);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建媒体类型失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> update(BaseFinancialindex baseFinancialindex) {
        if (baseFinancialindex.getId() == null) {
        return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseFinancialindexRepo.updateSelective(baseFinancialindex);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新媒体类型失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseFinancialindex> findById(Integer id) {
        ResultJson<BaseFinancialindex> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseFinancialindexRepo.findOne(id));
        return result;
    }
}
