package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseMonitorRequestMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMonitorRequest record);

    int insertSelective(BaseMonitorRequest record);

    BaseMonitorRequest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMonitorRequest record);

    int updateByPrimaryKey(BaseMonitorRequest record);

    // 以下为自定义SQL
    List<BaseMonitorRequest> queryAll(@Param("status") int status);
}