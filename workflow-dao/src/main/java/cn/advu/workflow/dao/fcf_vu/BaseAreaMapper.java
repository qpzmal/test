package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAreaMapper extends BaseDAO<BaseArea> {

    // 以下为自定义SQL
    List<BaseArea> queryAll(@Param("status") Integer status);
}