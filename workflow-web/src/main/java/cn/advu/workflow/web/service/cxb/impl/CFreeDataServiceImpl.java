package cn.advu.workflow.web.service.cxb.impl;

import cn.advu.workflow.dao.database.CxbAzAdMapper;
import cn.advu.workflow.dao.database.CxbFreeDataMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.service.cxb.CFreeDataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CFreeDataServiceImpl implements CFreeDataService{

    @Autowired
    private CxbFreeDataMapper fdMapper;
    
    @Autowired
    private CxbAzAdMapper azMapper;

    private static Logger LOGGER = Logger.getLogger(CFreeDataServiceImpl.class);
    
    @Override
    public ResultJson<List<Map<String, Object>>> getSdkData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        String adType = (String) paramMap.get("adType");
        int startRow = (pageNo - 1) * WebConstants.BIG_PAGESIZE;
        List<Map<String, Object>> sdkData = fdMapper.getSdkDataByPage(adType,version, startTime, endTime, cnids, startRow,WebConstants.BIG_PAGESIZE);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(sdkData);
        return rj;
    }

    @Override
    public ResultJson<Integer> getSdkPageCnt(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        String adType = (String) paramMap.get("adType");
        Integer itemCnt = fdMapper.getSdkPageCnt(adType,version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.BIG_PAGESIZE==0?itemCnt/WebConstants.BIG_PAGESIZE:itemCnt/WebConstants.BIG_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportSdkData(Map<String, Object> paramMap) {
        List<Map<String, Object>> sdkData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            String adType = (String) paramMap.get("adType");
            sdkData = fdMapper.getSdkDataByPage(adType,version, startTime, endTime, cnids,0,WebConstants.NO_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
        }

        return sdkData;
    }

    @Override
    public ResultJson<Integer> getInstallDataPageCnt(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        String earnName = (String)paramMap.get("earnName");
        Integer itemCnt = azMapper.getItemCnt(earnName,version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.BIG_PAGESIZE==0?itemCnt/WebConstants.BIG_PAGESIZE:itemCnt/WebConstants.BIG_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getInstallDataByPage(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        String earnName = (String) paramMap.get("earnName");
        int startRow = (pageNo - 1) * WebConstants.BIG_PAGESIZE;
        List<Map<String, Object>> sdkData = azMapper.getInstallDataByPage(earnName,version, startTime, endTime, cnids, startRow, WebConstants.BIG_PAGESIZE);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(sdkData);
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportInstallData(Map<String, Object> paramMap) {
        List<Map<String, Object>> installData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            String earnName = (String) paramMap.get("earnName");
            installData = azMapper.getInstallDataByPage(earnName,version, startTime, endTime, cnids,0,WebConstants.NO_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return installData;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getAdType() {
        List<Map<String, Object>> sdkData = fdMapper.getAdType();
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(sdkData);
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getEarnName() {
        List<Map<String, Object>> data = azMapper.getEarnName();
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        rj.setCode(WebConstants.OPERATION_SUCCESS);
        rj.setData(data);
        return rj;
    }

    @Override
    public ResultJson<Integer> getCollectDataPageCnt(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer itemCnt = fdMapper.getCollectDataPageCnt(version, startTime, endTime, cnids);
        int pageCnt = itemCnt%WebConstants.BIG_PAGESIZE==0?itemCnt/WebConstants.BIG_PAGESIZE:itemCnt/WebConstants.BIG_PAGESIZE+1;
        ResultJson<Integer> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(pageCnt);
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getCollectDataByPage(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        Integer pageNo = (Integer) paramMap.get("pageNo");
        int startRow = (pageNo - 1) * WebConstants.BIG_PAGESIZE;
        List<Map<String, Object>> sdkData = fdMapper.getCollectDataByPage(version, startTime, endTime, cnids, startRow, WebConstants.BIG_PAGESIZE);
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
        rj.setData(sdkData);
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportCollectData(Map<String, Object> paramMap) {
        List<Map<String, Object>> sdkcollectData = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            sdkcollectData = fdMapper.getCollectDataByPage(version, startTime, endTime, cnids,0,WebConstants.NO_PAGESIZE);
        } catch (Exception e) {
            LOGGER.error(e);
            return sdkcollectData;
        }
        return sdkcollectData;
    }
}
