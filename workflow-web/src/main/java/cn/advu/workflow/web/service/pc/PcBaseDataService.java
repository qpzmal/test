package cn.advu.workflow.web.service.pc;

import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.domain.database.PcBaseData;

public interface PcBaseDataService {

    PcBaseData getPcBaseDataByChannelAndDate(String date, String date1, String cnid);

    Page getPcBaserDatas(Page page, String startTime, String endTime, String[] cnids);

    Page getPcChannelMonthDatas(Page page, String startTime, String[] cnids);

    Page getPcTotalDatas(Page page, String startTime, String endTime, String[] cnids);
}
