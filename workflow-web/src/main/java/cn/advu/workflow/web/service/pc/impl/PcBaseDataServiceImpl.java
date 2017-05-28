package cn.advu.workflow.web.service.pc.impl;

import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.dao.database.PcBaseDataMapper;
import cn.advu.workflow.domain.database.PcBaseData;
import cn.advu.workflow.web.service.pc.PcBaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BBD on 2017/5/5.
 */
@Service
public class PcBaseDataServiceImpl implements PcBaseDataService {

    @Autowired
    private PcBaseDataMapper pcBaseDataMapper;

    @Override
    public PcBaseData getPcBaseDataByChannelAndDate(String date, String date1, String cnid) {
        return pcBaseDataMapper.getZwscBaseDataByChannelAndDate(date,date1,cnid);
    }

    @Override
    public Page getPcBaserDatas(Page page , String startTime, String endTime, String[] cnids) {
        page.setData(pcBaseDataMapper.getPcBaseDataList(page.getStart(),page.getPageSize(),startTime,endTime,cnids));
        page.setTotal(pcBaseDataMapper.getPcBaseDataCount(startTime,endTime,cnids));
        return page;
    }

    @Override
    public Page getPcChannelMonthDatas(Page page, String startTime, String[] cnids) {
        page.setData(pcBaseDataMapper.getPcChannelMonthDatas(page.getStart(),page.getPageSize(),startTime,cnids));
        page.setTotal(pcBaseDataMapper.getPcChannelMonthDatasCount(startTime,cnids));
        return page;
    }

    @Override
    public Page getPcTotalDatas(Page page, String startTime, String endTime, String[] cnids) {
        page.setData(pcBaseDataMapper.getPcChannelTotalDatas(page.getStart(),page.getPageSize(),startTime,endTime,cnids));
        page.setTotal(pcBaseDataMapper.getPcChannelTotalDatasCount(startTime,endTime,cnids));
        return page;
    }
}
