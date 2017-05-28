package cn.advu.workflow.web.service.pc.impl;

import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.dao.database.PcConsumerDataMapper;
import cn.advu.workflow.web.service.pc.PcConsumerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BBD on 2017/5/16.
 */
@Service
public class PcConsumerDataServiceImpl implements PcConsumerDataService {

    @Autowired
    private PcConsumerDataMapper pcConsumerDataMapper;
    @Override
    public Page getPcConsumerDatas(Page page, String startTime, String endTime, String[] cnids) {
        page.setData(pcConsumerDataMapper.getPcConsumerDataByTimeAndCnids(page.getStart(),page.getPageSize(),startTime,endTime,cnids));
        page.setTotal(pcConsumerDataMapper.getPcConsumerDataTotalByTimeAndCnids(startTime,endTime,cnids));
        return page;
    }
}
