package cn.advu.workflow.web.service.impl;

import cn.advu.workflow.dao.database.ZwscBaseDataMapper;
import cn.advu.workflow.domain.database.ZwscBaseData;
import cn.advu.workflow.web.service.ZwscDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZwscDataServiceImpl implements ZwscDataService{
    
    @Autowired
    private ZwscBaseDataMapper baseDataMapper;
    
    @Override
    public List<String> getVersions() {
        return baseDataMapper.getVersions();
    }

    @Override
    public ZwscBaseData getZwscBaseDataByChannelAndDate(String date, String date1, String cnid) {
        return baseDataMapper.getZwscBaseDataByChannelAndDate(date,date1,cnid);
    }

}
