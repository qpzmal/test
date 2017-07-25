package cn.advu.workflow.web.service.base.impl;

import cn.advu.workflow.dao.fcf_vu.BasePersonMapper;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.domain.fcf_vu.BasePerson;
import cn.advu.workflow.domain.fcf_vu.BasePersonExtend;
import cn.advu.workflow.repo.fcf_vu.BasePersonRepo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.service.base.PersonService;
import cn.advu.workflow.web.util.AssertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    BasePersonRepo basePersonRepo;

    @Override
    public ResultJson<List<BasePersonExtend>> findPersonByDept(Integer areaId, Integer deptId) {
        ResultJson<List<BasePersonExtend>> resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(basePersonRepo.findDeptPerson(areaId, deptId));
        return resultJson;
    }

    @Override
    public ResultJson<Integer> add(BasePerson basePerson) {
        ResultJson<Integer> resultJson = new ResultJson();
        resultJson.setData(basePersonRepo.addSelective(basePerson));
        return resultJson;
    }

    @Override
    public ResultJson<Void> update(BasePerson basePerson) {
        ResultJson<Void> resultJson = new ResultJson();
        basePersonRepo.updateSelective(basePerson);
        return resultJson;
    }

    @Override
    public ResultJson<BasePerson> findById(Integer id) {
        ResultJson<BasePerson> resultJson = new ResultJson();
        resultJson.setData(basePersonRepo.findOne(id));
        return resultJson;
    }

    @Override
    public ResultJson<Void> remove(Integer id) {
        BasePerson basePerson = basePersonRepo.findOne(id);
        AssertUtil.assertNotNull(basePerson, MessageConstants.PERSON_IS_NOT_EXISTS);
        basePersonRepo.logicRemove(basePerson);

        return new ResultJson<>();
    }
}
