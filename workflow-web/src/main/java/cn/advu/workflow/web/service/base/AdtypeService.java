package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 广告类型
 * Created by weiqz on 2017/6/25.
 */
public interface AdtypeService {

    ResultJson<Integer> addAdtype(BaseAdtype baseAdtype);
    ResultJson<List<BaseAdtype>> findAll();

}
