package cn.advu.workflow.dao.fcf_vu;

import cn.advu.workflow.dao.base.BaseDAO;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrderFrame;

import java.util.List;

public interface BaseBuyOrderFrameMapper extends BaseDAO<BaseBuyOrderFrame>  {

    // 以下为自定义SQL
    List<BaseBuyOrderFrame> queryAll(BaseBuyOrderFrame baseBuyOrderFrame);
}