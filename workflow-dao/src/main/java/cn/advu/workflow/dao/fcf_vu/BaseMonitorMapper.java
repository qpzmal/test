package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMonitorMapper extends BaseDAO<BaseMonitor> {

    // 以下为自定义SQL
    List<BaseMonitor> queryAll(@Param("status") Integer status);

    BaseMonitor queryByIdAndName(@Param("id")Integer id, @Param("name")String name);
}