package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseMedia;

public interface BaseMediaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMedia record);

    int insertSelective(BaseMedia record);

    BaseMedia selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMedia record);

    int updateByPrimaryKey(BaseMedia record);
}