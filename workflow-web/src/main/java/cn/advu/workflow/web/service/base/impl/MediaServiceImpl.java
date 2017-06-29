package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.repo.fcf_vu.BaseAreaRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class MediaServiceImpl implements MediaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaServiceImpl.class);

    @Autowired
    private BaseMediaRepo baseMediaRepo;

    @Override
    public ResultJson<List<BaseMedia>> findAll() {
        ResultJson<List<BaseMedia>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMediaRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> addMedia(BaseMedia baseMedia) {
        Integer insertCount = baseMediaRepo.addSelective(baseMedia);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建媒体失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> updateMedia(BaseMedia baseMedia) {
        if (baseMedia.getId() == null) {
        return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }
        Integer insertCount = baseMediaRepo.updateSelective(baseMedia);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新媒体失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseMedia> findById(Integer id) {
        ResultJson<BaseMedia> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMediaRepo.findOne(id));
        return result;
    }
}
