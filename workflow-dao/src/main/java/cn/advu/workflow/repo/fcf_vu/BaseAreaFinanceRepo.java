package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.repo.base.IRepo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BaseAreaFinanceRepo extends IRepo<BaseAreaFinance> {

    List<BaseAreaFinance> findByArea(Integer areaId);

    BaseAreaFinance findByAreaAndDate(Integer id, Integer areaId, Date startDate, Date endDate);

    BaseAreaFinance findByIdAndName(Integer id, String name);
}
