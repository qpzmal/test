package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 地区管理
 * Created by weiqz on 2017/6/25.
 */
public interface RegionService {

    ResultJson<Integer> addRegion(BaseRegion baseRegion);
    ResultJson<List<BaseRegion>> findAll();

//    int delete(String id);
//    int update(String id);
//    List<BaseRegion> queryByCondition(BaseRegion obj);

}
