package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseBuyOrderMapper extends BaseDAO<BaseBuyOrder> {
    // 以下为自定义SQL
    List<BaseBuyOrder> queryAll(@Param("status") Integer status);
}