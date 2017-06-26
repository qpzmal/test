package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.repo.fcf_vu.BaseAdtypeRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
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


    @Override
    public ResultJson<Integer> addAdtype(BaseAdtype baseAdtype) {
        Integer insertCount = baseAdtypeRepo.addSelective(baseAdtype);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建媒体失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<BaseAdtype>> findAll() {
        ResultJson<List<BaseAdtype>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseAdtypeRepo.findAll());
        return result;
    }
}
