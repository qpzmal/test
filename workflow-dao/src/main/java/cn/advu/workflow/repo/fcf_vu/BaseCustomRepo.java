package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseCustomRepo extends IRepo<BaseCustom> {

    List<BaseCustom> findAll();

    List<BaseCustom> findParentCustom();

    BaseCustom findByIdAndName(Integer id, String name);

    List<BaseCustom> find4AChildCustom(Integer id);

    List<BaseCustom> findAllChildCustom(Integer id);
}
