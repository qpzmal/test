package cn.advu.workflow.web.manager;

import cn.advu.workflow.repo.fcf_vu.BaseExecuteOrderActivitiHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangry on 17/7/14.
 */
@Component
public class BaseExecuteOrderActivitiHistoryManager {

    @Autowired
    BaseExecuteOrderActivitiHistoryRepo baseExecuteOrderActivitiHistoryRepo;



}
