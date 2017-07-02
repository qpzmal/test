package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BasePerson;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BasePersonMapper extends BaseDAO<BaseOrderCpm> {
    // 以下为自定义SQL
    List<BasePerson> queryAll(@Param("status") int status);
}