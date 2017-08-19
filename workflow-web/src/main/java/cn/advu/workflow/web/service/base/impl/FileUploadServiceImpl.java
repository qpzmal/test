package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.domain.fcf_vu.BaseFileupload;
import cn.advu.workflow.repo.fcf_vu.FileUploadRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.service.base.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadServiceImpl.class);

    @Autowired
    private FileUploadRepo fileUploadRepo;


    @Override
    public ResultJson<List<BaseFileupload>> findByCustom(String bizName) {
        ResultJson<List<BaseFileupload>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(fileUploadRepo.findByCustom(bizName));
        return result;
    }

    @Override
    public ResultJson<Integer> add(BaseFileupload baseFileupload) {
        Integer insertCount = fileUploadRepo.addSelective(baseFileupload);
        if(insertCount != 1){
            return new ResultJson<>(WebConstants.OPERATION_FAILURE, "存储文件路径失败!");
        }
        return new ResultJson<>(WebConstants.OPERATION_SUCCESS);
    }

    @Override
    public ResultJson<BaseFileupload> findById(Integer id) {
        ResultJson<BaseFileupload> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        result.setData(fileUploadRepo.findOne(id));
        return result;
    }

    @Override
    public ResultJson<Void> removeByName(String bizName, String bizType) {
        ResultJson<List<BaseFileupload>> result = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        fileUploadRepo.removeByName(bizName, bizType);
        return new ResultJson(WebConstants.OPERATION_SUCCESS);
    }
}
