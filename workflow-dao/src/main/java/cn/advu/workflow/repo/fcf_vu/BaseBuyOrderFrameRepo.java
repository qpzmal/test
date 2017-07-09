package cn.advu.workflow.repo.fcf_vu;


import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;
import cn.advu.workflow.repo.base.IRepo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseBuyOrderFrameRepo extends IRepo<BaseBuyOrderFrame> {

    List<BaseBuyOrderFrame> findAll();
}
