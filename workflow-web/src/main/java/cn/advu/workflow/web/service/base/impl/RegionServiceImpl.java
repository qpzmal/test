package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.repo.fcf_vu.BaseRegionRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class RegionServiceImpl implements RegionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);

    @Autowired
    private BaseRegionRepo baseRegionRepo;

    @Override
    public ResultJson<Integer> addRegion(BaseRegion baseRegion) {
        Integer insertCount = baseRegionRepo.addSelective(baseRegion);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建区域失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<List<BaseRegion>> findAll() {
        ResultJson<List<BaseRegion>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseRegionRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<BaseRegion> findById(Integer id) {
        ResultJson<BaseRegion> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseRegionRepo.findOne(id));
        return result;
    }
}
