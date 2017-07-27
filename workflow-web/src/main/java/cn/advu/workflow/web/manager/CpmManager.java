package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
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

    public List<BaseOrderCpmVO> findOrderCustomCpm(Integer orderId) {
        return  baseOrderCpmRepo.findExecuteOrderCpm(orderId);
    }
    public List<BaseOrderCpmVO> findOrderBuyCpm(Integer orderId) {
        return  baseOrderCpmRepo.findByBuyOrderCpm(orderId);
    }
    public List<BaseOrderCpmVO> findOrderBuyFrameCpm(Integer orderId) {
        return  baseOrderCpmRepo.findByBuyOrderFrameCpm(orderId);
    }


    public List<BaseOrderCpmVO> findExecuteOrderFrameCpm(Integer orderId) {
        return  baseOrderCpmRepo.findByExecuteOrderFrameCpm(orderId);
    }

    public List<BaseOrderCpmVO> findPassBuyOrderCpm() {
        return  baseOrderCpmRepo.findPassBuyOrderCpm();
    }

    public BaseOrderCpm findById(Integer id) {
        return baseOrderCpmRepo.findOne(id);
    }
}
