package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseTv;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 厂商管理
 * Created by weiqz on 2017/6/25.
 */
public interface TvService {

    ResultJson<Integer> addTv(BaseTv baseTv);
    ResultJson<List<BaseTv>> findAll();

}
