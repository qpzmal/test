package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseMediaType;

public interface BaseMediaTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMediaType record);

    int insertSelective(BaseMediaType record);

    BaseMediaType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMediaType record);

    int updateByPrimaryKey(BaseMediaType record);
}