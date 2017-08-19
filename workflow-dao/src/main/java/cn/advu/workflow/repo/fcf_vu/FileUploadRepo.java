package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseFileupload;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUploadRepo extends IRepo<BaseFileupload> {

    List<BaseFileupload> findByCustom(String bizName);
    int removeByName(String bizName, String bizType);
}
