package cn.advu.workflow.web.service.pc.impl;

import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.dao.database.PcBookReportMapper;
import cn.advu.workflow.web.service.pc.PcBookReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by BBD on 2017/5/17.
 */
@Service
public class PcBookReprotServiceImpl implements PcBookReportService {
    @Autowired
    private PcBookReportMapper pcBookReportMapper;
    @Override
    public Page getPcBookDatas(Page page, String startTime, String endTime, String bookId, String bookName) {
        page.setData(pcBookReportMapper.getPcBookDataByTimeAndBookIdBookName(page.getStart(),page.getPageSize(),startTime,endTime,bookId,bookName));
        page.setTotal(pcBookReportMapper.getPcBookDataTotalByTimeAndBookIdBookName(startTime,endTime,bookId,bookName));
        return page;
    }
}
