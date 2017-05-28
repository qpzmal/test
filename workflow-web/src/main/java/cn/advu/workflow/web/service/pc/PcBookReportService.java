package cn.advu.workflow.web.service.pc;

import cn.advu.workflow.common.golbal.Page;

/**
 * Created by BBD on 2017/5/16.
 */
public interface PcBookReportService {
    Page getPcBookDatas(Page page, String startTime, String endTime, String bookId, String bookName);
}
