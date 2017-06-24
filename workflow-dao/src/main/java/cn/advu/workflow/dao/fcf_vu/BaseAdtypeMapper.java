package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseAdtype;

public interface BaseAdtypeMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseAdtype record);

    int insertSelective(BaseAdtype record);

    BaseAdtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseAdtype record);

    int updateByPrimaryKey(BaseAdtype record);
}