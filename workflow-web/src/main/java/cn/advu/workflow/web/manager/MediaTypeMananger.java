package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.repo.fcf_vu.BaseAdtypeRepo;
import cn.advu.workflow.repo.fcf_vu.BaseMediaTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/18.
 */
@Component
public class MediaTypeMananger {

    @Autowired
    private BaseMediaTypeRepo baseMediaTypeRepo;


    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseMediaType baseMediaType = baseMediaTypeRepo.findByIdAndName(id, name);
        if (baseMediaType != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }
}
