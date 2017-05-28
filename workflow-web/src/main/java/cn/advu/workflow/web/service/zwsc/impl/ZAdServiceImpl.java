package cn.advu.workflow.web.service.zwsc.impl;

import cn.advu.workflow.dao.database.ZwscAdMapper;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.WebConstants;
import cn.advu.workflow.web.service.zwsc.ZAdService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglei on 2017/3/29.
 */
@Service
public class ZAdServiceImpl implements ZAdService {

    @Autowired
    private ZwscAdMapper zwscAdMapper;

    private static Logger LOGGER = Logger.getLogger(ZAdServiceImpl.class);

    @Override
    public ResultJson<List<Map<String, Object>>> getSdkData(Map<String, Object> paramMap) {
        ResultJson<List<Map<String, Object>>> rj = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer pageNo = (Integer) paramMap.get("pageNo");
            String adType = (String) paramMap.get("adType");
            int startRow = (pageNo - 1) * WebConstants.BIG_PAGESIZE;
            List<Map<String, Object>> sdkData =
                    zwscAdMapper.getSdkDataByPage(adType, version, startTime, endTime, cnids, startRow, WebConstants.BIG_PAGESIZE);
            rj = new ResultJson<>();
            rj.setCode(WebConstants.OPERATION_SUCCESS);
            rj.setData(sdkData);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getAdType() {
        ResultJson<List<Map<String, Object>>> rj = null;
        try {
            List<Map<String, Object>> sdkData = zwscAdMapper.getAdType();
            rj = new ResultJson<>();
            rj.setCode(WebConstants.OPERATION_SUCCESS);
            rj.setData(sdkData);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public ResultJson<Integer> getSdkPageCnt(Map<String, Object> paramMap) {
        ResultJson<Integer> rj = null;
        try {
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            String adType = (String) paramMap.get("adType");
            Integer itemCnt = zwscAdMapper.getSdkPageCnt(adType, version, startTime, endTime, cnids);
            int pageCnt = itemCnt % WebConstants.BIG_PAGESIZE == 0 ? itemCnt / WebConstants.BIG_PAGESIZE : itemCnt / WebConstants.BIG_PAGESIZE + 1;
            rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
            rj.setData(pageCnt);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportSdkData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        String adType = (String) paramMap.get("adType");
        List<Map<String, Object>> sdkData =
                zwscAdMapper.getSdkDataByPage(adType, version, startTime, endTime, cnids, 0, WebConstants.NO_PAGESIZE);
        return sdkData;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getEarnName() {
        ResultJson<List<Map<String, Object>>> rj = new ResultJson<>();
        try{
            List<Map<String, Object>> data = zwscAdMapper.getEarnName();
            rj.setCode(WebConstants.OPERATION_SUCCESS);
            rj.setData(data);
        }catch (Exception e){
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public ResultJson<Integer> getInstallDataPageCnt(Map<String, Object> paramMap) {
        ResultJson<Integer> rj = null;
        try{
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            String earnName = (String)paramMap.get("earnName");
            Integer itemCnt = zwscAdMapper.getItemCnt(earnName,version, startTime, endTime, cnids);
            int pageCnt = itemCnt%WebConstants.BIG_PAGESIZE==0?itemCnt/WebConstants.BIG_PAGESIZE:itemCnt/WebConstants.BIG_PAGESIZE+1;
            rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
            rj.setData(pageCnt);
        }catch (Exception e){
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getInstallDataByPage(Map<String, Object> paramMap) {
        ResultJson<List<Map<String, Object>>> rj = null;
        try{
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer pageNo = (Integer) paramMap.get("pageNo");
            String earnName = (String) paramMap.get("earnName");
            int startRow = (pageNo - 1) * WebConstants.BIG_PAGESIZE;
            List<Map<String, Object>> sdkData  = zwscAdMapper.getInstallDataByPage(earnName,version, startTime, endTime, cnids, startRow, WebConstants.BIG_PAGESIZE);
            rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
            rj.setData(sdkData);
        }catch (Exception e){
            LOGGER.error(e);
        }

        return rj;
    }

    @Override
    public List<Map<String, Object>> exportInstallData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        String earnName = (String) paramMap.get("earnName");
        List<Map<String, Object>> installData =
                zwscAdMapper.getInstallDataByPage(earnName,version, startTime, endTime, cnids,0,WebConstants.NO_PAGESIZE);
        return installData;
    }

    @Override
    public ResultJson<Integer> getCollectDataPageCnt(Map<String, Object> paramMap) {
        ResultJson<Integer> rj = null;
        try{
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer itemCnt = zwscAdMapper.getCollectDataPageCnt(version, startTime, endTime, cnids);
            int pageCnt = itemCnt%WebConstants.BIG_PAGESIZE==0?itemCnt/WebConstants.BIG_PAGESIZE:itemCnt/WebConstants.BIG_PAGESIZE+1;
            rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
            rj.setData(pageCnt);
        }catch (Exception e){
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public ResultJson<List<Map<String, Object>>> getCollectDataByPage(Map<String, Object> paramMap) {
        ResultJson<List<Map<String, Object>>> rj = null;
        try{
            String version = (String) paramMap.get("version");
            String startTime = (String) paramMap.get("startTime");
            String endTime = (String) paramMap.get("endTime");
            String[] cnids = (String[]) paramMap.get("cnids");
            Integer pageNo = (Integer) paramMap.get("pageNo");
            int startRow = (pageNo - 1) * WebConstants.BIG_PAGESIZE;
            List<Map<String, Object>> sdkData = zwscAdMapper.getCollectDataByPage(version, startTime, endTime, cnids, startRow, WebConstants.BIG_PAGESIZE);
            rj = new ResultJson<>(WebConstants.OPERATION_SUCCESS);
            rj.setData(sdkData);
        }catch (Exception e){
            LOGGER.error(e);
        }
        return rj;
    }

    @Override
    public List<Map<String, Object>> exportCollectData(Map<String, Object> paramMap) {
        String version = (String) paramMap.get("version");
        String startTime = (String) paramMap.get("startTime");
        String endTime = (String) paramMap.get("endTime");
        String[] cnids = (String[]) paramMap.get("cnids");
        List<Map<String, Object>> collectData =
                zwscAdMapper.getCollectDataByPage(version, startTime, endTime, cnids,0,WebConstants.NO_PAGESIZE);
        return collectData;
    }
}
