package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseRegion;

import java.util.List;

/**
 * 地区管理
 * Created by weiqz on 2017/6/25.
 */
public interface RegionService {

    int insert(BaseRegion obj);
    int delete(String id);
    int update(String id);
    List<BaseRegion> queryAll();

    List<BaseRegion> queryByCondition(BaseRegion obj);
}
