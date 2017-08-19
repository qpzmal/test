package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseFileupload;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 区域结算信息
 * Created by weiqz on 2017/6/25.
 */
public interface FileUploadService {

   ResultJson<List<BaseFileupload>> findByCustom(String bizName, String bizType);

   ResultJson<Integer> add(BaseFileupload baseFileupload);

   ResultJson<BaseFileupload> findById(Integer id);

   ResultJson<Void> removeByName(String bizName, String bizType);
}
