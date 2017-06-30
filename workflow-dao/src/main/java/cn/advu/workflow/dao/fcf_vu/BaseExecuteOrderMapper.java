package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseExecuteOrderMapper extends BaseDAO<BaseExecuteOrder> {

    // 以下为自定义SQL
    List<BaseExecuteOrder> queryAll(@Param("status") Integer status);
}