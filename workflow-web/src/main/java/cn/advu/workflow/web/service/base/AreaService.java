package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseArea;

import java.util.List;

/**
 * 区域（公司）信息
 * Created by weiqz on 2017/6/25.
 */
public interface AreaService {

    int insert(BaseArea obj);
    int delete(String id);
    int update(String id);
    List<BaseArea> queryAll();

    List<BaseArea> queryByCondition(BaseArea obj);
}
