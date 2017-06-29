package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMediaMapper extends BaseDAO<BaseMedia>{

    // 以下为自定义SQL
    List<BaseMedia> queryAll(@Param("status") Integer status);
}