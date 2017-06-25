package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.BaseTv;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseTvMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseTv record);

    int insertSelective(BaseTv record);

    BaseTv selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseTv record);

    int updateByPrimaryKey(BaseTv record);

    // 以下为自定义SQL
    List<BaseTv> queryAll(@Param("status") int status);
}