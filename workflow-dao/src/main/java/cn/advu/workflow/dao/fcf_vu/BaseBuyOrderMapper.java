package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseBuyOrderMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseBuyOrder record);

    int insertSelective(BaseBuyOrder record);

    BaseBuyOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseBuyOrder record);

    int updateByPrimaryKey(BaseBuyOrder record);

    // 以下为自定义SQL
    List<BaseBuyOrder> queryAll(@Param("status") int status);
}