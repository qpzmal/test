package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseExecuteOrderRepo extends IRepo<BaseExecuteOrder> {

    List<BaseExecuteOrder> findAll(BaseExecuteOrder baseExecuteOrder);
    List<BaseExecuteOrder> findAllUnFinished();
}
