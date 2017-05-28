package cn.advu.workflow.web.service.impl;

import cn.advu.workflow.dao.database.CxbBaseDataMapper;
import cn.advu.workflow.web.service.CxbDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CxbDataServiceImpl implements CxbDataService{
    
    @Autowired
    private CxbBaseDataMapper baseDataMapper;
    
    @Override
    public List<String> getVersions() {
        return baseDataMapper.getVersions();
    }

}
