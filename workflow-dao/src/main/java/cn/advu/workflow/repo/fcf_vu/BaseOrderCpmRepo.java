package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseOrderCpmRepo extends IRepo<BaseOrderCpm> {

    List<BaseOrderCpm> findByCustomOrderCpm(Integer orderId);

}
