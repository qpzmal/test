package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseFileupload;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseFileuploadMapper  extends BaseDAO<BaseFileupload> {

    List<BaseFileupload> findByCustom(@Param("bizName") String bizName);
    int removeByName(@Param("bizName") String bizName, @Param("bizType") String bizType);
}