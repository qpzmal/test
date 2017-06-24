package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.domain.fcf_vu.OaNotifyRecord;

public interface OaNotifyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaNotifyRecord record);

    int insertSelective(OaNotifyRecord record);

    OaNotifyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OaNotifyRecord record);

    int updateByPrimaryKey(OaNotifyRecord record);
}