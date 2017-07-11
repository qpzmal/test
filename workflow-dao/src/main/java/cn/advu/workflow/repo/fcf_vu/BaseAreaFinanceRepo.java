package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAreaFinanceRepo extends IRepo<BaseAreaFinance> {

    List<BaseAreaFinance> findByArea(Integer areaId);
}