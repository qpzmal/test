package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseExecuteOrderRepo extends IRepo<BaseExecuteOrder> {

    List<BaseExecuteOrder> findAll(BaseExecuteOrder baseExecuteOrder);
    List<BaseExecuteOrder> queryAllForContract(BaseExecuteOrder baseExecuteOrder);
    List<BaseExecuteOrder> findAllUnFinished();
    List<Map> finalReport(String likeSearch, List<String> mediaIdList);
    List<Map> finalReport(String days);
}
