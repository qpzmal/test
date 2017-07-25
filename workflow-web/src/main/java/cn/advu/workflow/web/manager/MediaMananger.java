package cn.advu.workflow.web.manager;

import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.repo.fcf_vu.BaseMediaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangry on 17/7/18.
 */
@Component
public class MediaMananger {

    @Autowired
    private BaseMediaRepo baseMediaRepo;

    /**
     * 返回可用的媒体列表
     *
     * @return
     */
    public List<BaseMedia> findAllActiveMedia() {
        return  baseMediaRepo.findAllActive();
    }

    public Boolean isNameDuplicated(Integer id, String name) {
        Boolean isNameDuplicated = false;
        BaseMedia baseMedia = baseMediaRepo.findByIdAndName(id, name);
        if (baseMedia != null) {
            isNameDuplicated = true;
        }
        return isNameDuplicated;
    }

    public Boolean isCodeDuplicated(Integer id, String code) {
        Boolean isCodeDuplicated = false;
        BaseMedia baseMedia = baseMediaRepo.findByIdAndCode(id, code);
        if (baseMedia != null) {
            isCodeDuplicated = true;
        }
        return isCodeDuplicated;
    }
}
