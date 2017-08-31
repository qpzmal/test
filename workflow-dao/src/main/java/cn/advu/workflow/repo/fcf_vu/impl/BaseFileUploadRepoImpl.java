package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseFileuploadMapper;
import cn.advu.workflow.domain.fcf_vu.BaseFileupload;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.FileUploadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 媒体
 *
 */
@Repository
public class BaseFileUploadRepoImpl extends AbstractRepo<BaseFileupload> implements FileUploadRepo {

    @Autowired
    BaseFileuploadMapper baseFileuploadMapper;

    @Override
    protected BaseDAO<BaseFileupload> getSqlMapper() {
        return baseFileuploadMapper;
    }

    @Override
    public List<BaseFileupload> findByCustom(String bizName, String bizType) {
        return baseFileuploadMapper.findByCustom(bizName, bizType);
    }

    @Override
    public int removeByName(String bizName, String bizType) {
        return baseFileuploadMapper.removeByName(bizName, bizType);
    }
}
