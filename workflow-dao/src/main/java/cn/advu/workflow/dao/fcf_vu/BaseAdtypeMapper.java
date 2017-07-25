package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 媒体Mapper
 */
public interface BaseAdtypeMapper extends BaseDAO<BaseAdtype> {
    // 以下为自定义SQL
    List<BaseAdtype> queryAll(@Param("status") Integer status);


    BaseAdtype queryByIdAndName(@Param("id")Integer id, @Param("name")String name);
}