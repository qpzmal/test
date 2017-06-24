package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.ISqlMapper;
import cn.advu.workflow.domain.fcf_vu.OaNotify;

public interface OaNotifyMapper extends ISqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaNotify record);

    int insertSelective(OaNotify record);

    OaNotify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OaNotify record);

    int updateByPrimaryKey(OaNotify record);
}