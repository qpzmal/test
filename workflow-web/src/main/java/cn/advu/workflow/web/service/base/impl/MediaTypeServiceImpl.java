package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.enums.ItemStatusEnum;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.repo.fcf_vu.BaseMediaRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaTypeRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.exception.ServiceException;
import cn.advu.workflow.web.manager.MediaMananger;
import cn.advu.workflow.web.manager.MediaTypeMananger;
import cn.advu.workflow.web.service.base.MediaService;
import cn.advu.workflow.web.service.base.MediaTypeService;
import cn.advu.workflow.web.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class MediaTypeServiceImpl implements MediaTypeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MediaTypeServiceImpl.class);

    @Autowired
    private BaseMediaTypeRepo baseMediaTypeRepo;
    @Autowired
    MediaTypeMananger mediaTypeMananger;

    @Autowired
    MediaMananger mediaMananger;

    @Override
    public ResultJson<List<BaseMediaType>> findAll() {
        ResultJson<List<BaseMediaType>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMediaTypeRepo.findAll());
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseMediaType baseMediaType) {
        if (mediaTypeMananger.isNameDuplicated(null, baseMediaType.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }
        Integer insertCount = baseMediaTypeRepo.addSelective(baseMediaType);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "创建媒体类型失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<Integer> update(BaseMediaType baseMediaType) {
        if (baseMediaType.getId() == null) {
        return new ResultJson<>(WebConstants.OPERATION_FAILURE, "ID没有设置!");
        }

        if (mediaTypeMananger.isNameDuplicated(baseMediaType.getId(), baseMediaType.getName())) {
            throw new ServiceException(MessageConstants.NAME_IS_DUPLICATED);
        }

        Integer insertCount = baseMediaTypeRepo.updateSelective(baseMediaType);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "更新媒体类型失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseMediaType> findById(Integer id) {
        ResultJson<BaseMediaType> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMediaTypeRepo.findOne(id));
        return result;
    }

    @Override
    public ResultJson<List<BaseMediaType>> findActiveType() {
        ResultJson<List<BaseMediaType>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(baseMediaTypeRepo.findActiveType());
        return result;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {

        BaseMediaType baseMediaType = baseMediaTypeRepo.findOne(id);
        AssertUtil.assertNotNull(baseMediaType, MessageConstants.MEDIA_TYPE_IS_NOT_EXISTS);

        if (mediaMananger.hasMediaByType(id)) {
            throw new ServiceException(MessageConstants.MEDIA_TYPE_IS_USEDBY_MEDIA);
        }

        baseMediaTypeRepo.logicRemove(baseMediaType);
        return new ResultJson<>();
    }
}
