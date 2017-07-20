package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseOrderCpmRepo extends IRepo<BaseOrderCpm> {

    List<BaseOrderCpmVO> findByCustomOrderCpm(Integer orderId);

    List<BaseOrderCpmVO> findByBuyOrderCpm(Integer orderId);

    List<BaseOrderCpmVO> findByExecuteOrderFrameCpm(Integer orderId);

    List<BaseOrderCpmVO> findExecuteOrderCpm(Integer orderId);

    List<BaseOrderCpmVO> findByBuyOrderFrameCpm(Integer orderId);


}
