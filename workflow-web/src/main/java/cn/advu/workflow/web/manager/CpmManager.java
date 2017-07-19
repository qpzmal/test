package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.repo.fcf_vu.BaseOrderCpmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/18.
 */
@Component
public class CpmManager {
    @Autowired
    BaseOrderCpmRepo baseOrderCpmRepo;

    public List<BaseOrderCpm> findOrderCustomCpm(Integer orderId) {
        return  baseOrderCpmRepo.findByCustomOrderCpm(orderId);
    }
    public List<BaseOrderCpm> findOrderBuyCpm(Integer orderId) {
        return  baseOrderCpmRepo.findByBuyOrderCpm(orderId);
    }
}
