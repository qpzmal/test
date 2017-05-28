package cn.advu.workflow.web.service.pc.impl;

import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.dao.database.PcRechargeMapper;
import cn.advu.workflow.web.service.pc.PcRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BBD on 2017/5/17.
 */
@Service
public class PcRechargeServiceImpl implements PcRechargeService {
    @Autowired
    private PcRechargeMapper pcRechargeMapper;
    @Override
    public Page getPcRechargeDatas(Page page, String startTime, String endTime, Integer type, String[] cnids) {
        page.setData(pcRechargeMapper.getPcRechargeByTimeAndCnidsAndType(page.getStart(),page.getPageSize(),startTime,endTime,type,cnids));
        page.setTotal(pcRechargeMapper.getPcRechargeTotalByTimeAndCnidsAndType(startTime,endTime,type,cnids));
        return page;
    }
}
