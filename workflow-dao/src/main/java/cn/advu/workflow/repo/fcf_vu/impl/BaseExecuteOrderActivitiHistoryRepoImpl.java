package cn.advu.workflow.repo.fcf_vu.impl;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.fcf_vu.BaseExecuteOrderActivitiHistoryMapper;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderActivitiHistory;
import cn.advu.workflow.repo.base.impl.AbstractRepo;
import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderActivitiHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 部门
 *
 */
@Repository
public class BaseExecuteOrderActivitiHistoryRepoImpl extends AbstractRepo<BaseExecuteOrderActivitiHistory> implements BaseExecuteOrderActivitiHistoryRepo {

    @Autowired
    BaseExecuteOrderActivitiHistoryMapper baseExecuteOrderActivitiHistoryMapper;


    @Override
    protected BaseDAO<BaseExecuteOrderActivitiHistory> getSqlMapper() {
        return baseExecuteOrderActivitiHistoryMapper;
    }
}
