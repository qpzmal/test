package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;

import java.util.List;

/**
 * 广告类型
 * Created by weiqz on 2017/6/25.
 */
public interface AdtypeService {

    int insert(BaseAdtype obj);
    int delete(String id);
    int update(String id);
    List<BaseAdtype> queryAll();

    List<BaseAdtype> queryByCondition(BaseAdtype obj);
}
