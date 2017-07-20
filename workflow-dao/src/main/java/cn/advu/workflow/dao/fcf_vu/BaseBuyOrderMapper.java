package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;

import java.util.List;

public interface BaseBuyOrderMapper extends BaseDAO<BaseBuyOrder> {
    // 以下为自定义SQL
    List<BaseBuyOrder> queryAll(BaseBuyOrder baseBuyOrder);
}