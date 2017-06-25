package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseAreaMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseArea record);

    int insertSelective(BaseArea record);

    BaseArea selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseArea record);

    int updateByPrimaryKey(BaseArea record);

    // 以下为自定义SQL
    List<BaseArea> queryAll(@Param("status") int status);
}