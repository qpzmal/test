package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseExecuteOrderFrameMapper extends BaseDAO<BaseExecuteOrderFrame>{

    // 以下为自定义SQL
    List<BaseExecuteOrderFrame> queryAll(@Param("status") Integer status);

}