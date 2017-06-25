package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseTv;

import java.util.List;

/**
 * 厂商管理
 * Created by weiqz on 2017/6/25.
 */
public interface TvService {

    int insert(BaseTv obj);
    int delete(String id);
    int update(String id);
    List<BaseTv> queryAll();

    List<BaseTv> queryByCondition(BaseTv obj);
}
