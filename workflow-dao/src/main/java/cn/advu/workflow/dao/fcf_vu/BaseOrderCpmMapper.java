package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseOrderCpmMapper extends BaseDAO<BaseOrderCpm> {

    List<BaseOrderCpmVO> selectByOrderAndType(
            @Param("orderId")Integer orderId,
            @Param("orderCpmType")Integer orderCpmType);

    List<BaseOrderCpmVO> selectExecuteOrderCpm(@Param("orderId")Integer orderId);


    void deleteByOrderAndType(
            @Param("orderId")Integer orderId,
            @Param("orderCpmType")Integer orderCpmType);


}