package cn.advu.workflow.web.service;

import cn.advu.workflow.domain.database.ZwscBaseData;

import java.util.List;

public interface ZwscDataService {

    List<String> getVersions();

    ZwscBaseData getZwscBaseDataByChannelAndDate(String date, String date1, String cnid);
}
