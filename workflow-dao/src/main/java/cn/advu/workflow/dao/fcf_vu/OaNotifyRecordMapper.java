package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.OaNotifyRecord;

import java.util.List;

public interface OaNotifyRecordMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaNotifyRecord record);

    int insertSelective(OaNotifyRecord record);

    OaNotifyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OaNotifyRecord record);

    int updateByPrimaryKey(OaNotifyRecord record);

    // 以下为自定义SQL
    List<OaNotifyRecord> queryAll();
}