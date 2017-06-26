package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseTv;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 媒体分类Mapper
 */
public interface BaseTvMapper extends BaseDAO<BaseTv> {

    // 以下为自定义SQL
    List<BaseTv> queryAll(@Param("status") Integer status);
}