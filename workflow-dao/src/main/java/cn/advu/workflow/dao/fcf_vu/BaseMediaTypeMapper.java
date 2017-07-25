package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMediaTypeMapper extends BaseDAO<BaseMediaType> {


    // 以下为自定义SQL
    List<BaseMediaType> queryAll(@Param("status") Integer status);

    BaseMediaType queryByIdAndName(@Param("id")Integer id, @Param("name")String name);
}