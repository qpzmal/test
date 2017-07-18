package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import org.apache.ibatis.annotations.Param;

import java.lang.annotation.Inherited;
import java.util.List;

public interface BaseOrderCpmMapper extends BaseDAO<BaseOrderCpm> {

    List<BaseOrderCpm> selectByOrderAndType(
            @Param("orderId")Integer orderId,
            @Param("orderCpmType")Integer orderCpmType);

    void deleteByOrderAndType(
            @Param("orderId")Integer orderId,
            @Param("orderCpmType")Integer orderCpmType);
}