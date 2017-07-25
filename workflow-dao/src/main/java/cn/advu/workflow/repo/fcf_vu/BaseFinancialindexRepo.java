package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.repo.base.IRepo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseFinancialindexRepo extends IRepo<BaseFinancialindex> {

    List<BaseFinancialindex> findAll();

    BaseFinancialindex findByIdAndName(Integer id, String name);

    BaseFinancialindex findByIdAndNumber(Integer id, String number);
}
