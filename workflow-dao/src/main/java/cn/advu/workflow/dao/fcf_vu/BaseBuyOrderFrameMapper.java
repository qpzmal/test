package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;

public interface BaseBuyOrderFrameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseBuyOrderFrame record);

    int insertSelective(BaseBuyOrderFrame record);

    BaseBuyOrderFrame selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseBuyOrderFrame record);

    int updateByPrimaryKey(BaseBuyOrderFrame record);
}