package cn.advu.workflow.web.service.base;

import cn.advu.workflow.domain.fcf_vu.BasePerson;
import cn.advu.workflow.domain.fcf_vu.BasePersonExtend;
import cn.advu.workflow.web.common.ResultJson;

import java.util.List;

/**
 * 公司人员
 * Created by weiqz on 2017/6/25.
 */
public interface PersonService {

    ResultJson<List<BasePersonExtend>> findPersonByDept(Integer areaId, Integer deptId);
}
