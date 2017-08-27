package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BasePerson;
import cn.advu.workflow.repo.fcf_vu.BasePersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/13.
 */
@Component
public class PersonMananger {

    @Autowired
    BasePersonRepo basePersonRepo;

    /**
     * 返回部门下的通讯录列表
     *
     * @param deptId
     * @return
     */
    public List<BasePerson> findPersonByDept(Integer deptId) {
       return  basePersonRepo.findChildListByDept(deptId);
    }

    /**
     * 返回是否有下属通讯录
     *
     * @param deptId
     * @return
     */
    public Boolean hasPersonUnderDept(Integer deptId) {
        Boolean hasPerson = false;
        List<BasePerson> basePersonList = findPersonByDept(deptId);
        if (basePersonList != null && !basePersonList.isEmpty()) {
            hasPerson = true;
        }
        return hasPerson;
    }

    /**
     * 返回区域下的所有通讯录
     *
     * @param areaId
     * @return
     */
    public List<BasePerson> findPersonListByArea(Integer areaId) {
        return basePersonRepo.findPersonListByArea(areaId);
    }

    /**
     * 按照名字返回通讯录
     *
     * @param name
     * @return
     */
    public BasePerson findPersonByName(String name) {
        return basePersonRepo.findPersonByName(name);
    }


    /**
     * 判定客户名称是否重复
     *
     * @param id
     * @param name
     * @return
     */
    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BasePerson basePerson = basePersonRepo.findByIdAndName(id, name);
        if (basePerson != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }

    public BasePerson queryByUid(Integer uid) {
        return basePersonRepo.queryByUid(uid);
    }

    public Boolean hasSubordinate(Integer id) {

        Boolean hasSubordinate = false;
        List<BasePerson> basePersonList = basePersonRepo.findByParentId(id);
        if (basePersonList != null && !basePersonList.isEmpty()) {
            hasSubordinate = true;
        }
        return hasSubordinate;
    }

    public BasePerson findById(Integer id) {
        return basePersonRepo.findOne(id);
    }
}
