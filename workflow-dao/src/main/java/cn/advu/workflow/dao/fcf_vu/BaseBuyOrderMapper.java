package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;

public interface BaseBuyOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseBuyOrder record);

    int insertSelective(BaseBuyOrder record);

    BaseBuyOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseBuyOrder record);

    int updateByPrimaryKey(BaseBuyOrder record);
}