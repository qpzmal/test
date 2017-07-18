package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.repo.fcf_vu.BaseCustomRepo;
import cn.advu.workflow.web.service.base.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/13.
 */
@Component
public class CustomMananger {

    @Autowired
    BaseCustomRepo baseCustomRepo;

    /**
     * 判定客户名称是否重复
     *
     * @param customeId
     * @param name
     * @return
     */
    public Boolean isNameDuplicated(Integer customeId, String name) {
        Boolean isNameDuplicated = false;
        BaseCustom baseCustom = baseCustomRepo.findByIdAndName(customeId, name);
        if (baseCustom != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }

    /**
     * 返回某种类型的CUSTOM
     *
     * @param customType
     * @return
     */
    public List<BaseCustom> findCustomListByCustomType(Integer customType) {
        return baseCustomRepo.findCustomListByCustomType(customType);
    }

    /**
     * 返回客户的所有广告主
     *
     * @param customId
     * @return
     */
    public List<BaseCustom> findChildCustom(Integer customId) {
        return baseCustomRepo.findAllChildCustom(customId);
    }

    /**
     * 返回当前客户
     *
     * @param id
     * @return
     */
    public BaseCustom findById(Integer id) {
        return baseCustomRepo.findOne(id);
    }
}
