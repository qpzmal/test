package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAdtypeMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseAdtype record);

    int insertSelective(BaseAdtype record);

    BaseAdtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseAdtype record);

    int updateByPrimaryKey(BaseAdtype record);

    // 以下为自定义SQL
    List<BaseAdtype> queryAll(@Param("item_status") int status);

}