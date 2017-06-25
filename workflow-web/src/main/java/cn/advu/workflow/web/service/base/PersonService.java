package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BasePerson;

import java.util.List;

/**
 * 公司人员
 * Created by weiqz on 2017/6/25.
 */
public interface PersonService {

    int insert(BasePerson obj);
    int delete(String id);
    int update(String id);
    List<BasePerson> queryAll();

    List<BasePerson> queryByCondition(BasePerson obj);
}
