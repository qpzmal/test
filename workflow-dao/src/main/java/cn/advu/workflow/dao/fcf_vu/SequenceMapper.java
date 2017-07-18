package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;


/**
 * 媒体Mapper
 */
public interface SequenceMapper extends ISqlMapper {
    // 以下为自定义SQL
    Integer nextVal();
}