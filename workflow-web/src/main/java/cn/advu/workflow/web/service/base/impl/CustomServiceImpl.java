package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.CustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class CustomServiceImpl implements CustomService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomServiceImpl.class);

    @Autowired
    private BaseCustomRepo baseCustomRepo;

    @Override
    public ResultJson<List<BaseCustom>> findAll() {
        ResultJson<List<BaseCustom>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseCustomRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseCustom baseCustom) {
        Integer insertCount = baseCustomRepo.addSelective(baseCustom);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建客户失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> update(BaseCustom baseCustom) {
        if (baseCustom.getId() == null) {
        return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseCustomRepo.updateSelective(baseCustom);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新客户失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseCustom> findById(Integer id) {
        ResultJson<BaseCustom> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseCustomRepo.findOne(id));
        return result;
    }
}
