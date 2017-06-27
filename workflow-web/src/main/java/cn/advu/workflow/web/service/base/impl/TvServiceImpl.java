package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseTv;
import cn.advu.workflow.repo.fcf_vu.BaseTvRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.TvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class TvServiceImpl implements TvService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TvServiceImpl.class);

    @Autowired
    private BaseTvRepo baseTvRepo;


    @Override
    public ResultJson<Integer> addTv(BaseTv baseTv) {
        Integer insertCount = baseTvRepo.addSelective(baseTv);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建媒体类型失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<BaseTv>> findAll() {
        ResultJson<List<BaseTv>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseTvRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<BaseTv> findById(Integer id) {
        ResultJson<BaseTv> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseTvRepo.findOne(id));
        return result;
    }
}
