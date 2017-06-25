package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDeptMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseDept record);

    int insertSelective(BaseDept record);

    BaseDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseDept record);

    int updateByPrimaryKey(BaseDept record);

    // 以下为自定义SQL
    List<BaseDept> queryAll(@Param("status") int status);
}