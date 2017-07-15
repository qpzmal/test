package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BaseCustomFinanceRepo extends IRepo<BaseCustomFinance> {

    List<BaseCustomFinance> findByCustom(Integer customId);

    BaseCustomFinance findByCustomAndDate(Integer customId, Date startDate, Date endDate);
}
