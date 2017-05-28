package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.common.utils.ExportExcelUtil;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.controller.BaseController;
import cn.advu.workflow.web.service.zwsc.ZAdService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 中文书城广告
 * Created by zhanglei on 2017/3/29.
 */
@Controller
@RequestMapping("zwsc/ad")
public class ZAdcontroller extends BaseController {

    @Autowired
    private ZAdService zAdService;

    private static Logger LOGGER = Logger.getLogger(ZAdcontroller.class);

    @RequestMapping("/index")
    public String toIndex(){
        return "zwsc/ad/sdk";
    }

    /**
     * 获取sdk广告数据
     * @param request
     * @return
     */
    @RequestMapping("/getSdkDataByPage")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getSdkTableData(HttpServletRequest request){
        ResultJson<List<Map<String, Object>>> sdkData = null;
        try {
            Map<String, Object> paramMap = handleParams(request);
            Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
            String adType = request.getParameter("adType");
            String version= request.getParameter("version");
            paramMap.put("pageNo", pageNo);
            paramMap.put("version", version);
            paramMap.put("adType", adType);
            sdkData = zAdService.getSdkData(paramMap);
        } catch (NumberFormatException e) {
           LOGGER.error(e);
        }
        return sdkData;
    }

    /**
     * 获取sdk广告类型
     * @return
     */
    @RequestMapping("/getAdType")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getAdType(){
        return zAdService.getAdType();
    }

    /**
     * 获取sdk广告分页数据
     * @param request
     * @return
     */
    @RequestMapping("/getSdkPageCnt")
    @ResponseBody
    public ResultJson<Integer> getSdkPageCnt(HttpServletRequest request){
        ResultJson<Integer> count = null;
        try{
            Map<String, Object> paramMap = handleParams(request);
            String adType = request.getParameter("adType");
            String version = request.getParameter("version");
            paramMap.put("version", version);
            paramMap.put("adType", adType);
            count = zAdService.getSdkPageCnt(paramMap);
        }catch (Exception e){
            LOGGER.error(e);
        }
        return count;
    }

    /**
     * 导出sdk广告数据
     * @param request
     * @return
     */
    @RequestMapping("/exportSdkData")
    public void exportSdkData(HttpServletRequest request,HttpServletResponse response){
        List<Map<String, Object>> maps = null;
        try{
            Map<String, Object> paramMap = handleParams(request);
            String adType = request.getParameter("adType");
            paramMap.put("adType",adType);
            maps = zAdService.exportSdkData(paramMap);
            String name = "SDK广告数据";
            List<String> titles = new ArrayList<>();
            titles.add("日期");
            titles.add("广告类型");
            titles.add("曝光PV");
            titles.add("曝光UV");
            titles.add("点击PV");
            titles.add("点击UV");
            List<List<Object>> data = new ArrayList<>();
            for(Map<String, Object> item:maps){
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("dt").toString());
                rowData.add(item.get("adType").toString());
                rowData.add(item.get("bgpv").toString());
                rowData.add(item.get("bguv").toString());
                rowData.add(item.get("djpv").toString());
                rowData.add(item.get("djuv").toString());
                data.add(rowData);
            }
            ExportExcelUtil.export(response,name,titles,data);
        }catch (Exception e){
            LOGGER.error(e);
        }

    }

    /**
     * 获取广告名称
     * @return
     */
    @RequestMapping("/getEarnName")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getEarnName(){
        return zAdService.getEarnName();
    }


    @RequestMapping("/getInstallDataPageCnt")
    @ResponseBody
    public ResultJson<Integer> getInstallDataPageCnt(HttpServletRequest request){
        ResultJson<Integer> installDataPageCnt = null;
        try{
            Map<String, Object> paramMap = handleParams(request);
            String earnName = request.getParameter("earnName");
            String version = request.getParameter("version");
            paramMap.put("version", version);
            paramMap.put("earnName", earnName);
            installDataPageCnt = zAdService.getInstallDataPageCnt(paramMap);
        }catch (Exception e){
            LOGGER.error(e);
        }
        return installDataPageCnt;
    }

    @RequestMapping("/getInstallDataByPage")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getInstallDataByPage(HttpServletRequest request){
        ResultJson<List<Map<String, Object>>> installDataByPage = null;
        try{
            Map<String, Object> paramMap = handleParams(request);
            Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
            String earnName = request.getParameter("earnName");
            String version = request.getParameter("version");
            paramMap.put("version", version);
            paramMap.put("earnName", earnName);
            paramMap.put("pageNo", pageNo);
            installDataByPage = zAdService.getInstallDataByPage(paramMap);
        }catch (Exception e){
            LOGGER.error(e);
        }
        return installDataByPage;
    }

    @RequestMapping("/exportInstallData")
    public void exportInstallData(HttpServletRequest request,HttpServletResponse response){
        List<Map<String, Object>> maps = null;
        try{
            Map<String, Object> paramMap = handleParams(request);
            String earnName = request.getParameter("earnName");
            paramMap.put("earnName",earnName);
            maps = zAdService.exportInstallData(paramMap);
            String name = "直投类广告数据";
            List<String> titles = new ArrayList<>();
            titles.add("日期");
            titles.add("广告ID");
            titles.add("广告名称");
            titles.add("曝光PV");
            titles.add("曝光UV");
            titles.add("点击PV");
            titles.add("点击UV");
            titles.add("安装成功PV");
            titles.add("安装成功UV");
            List<List<Object>> data = new ArrayList<>();
            for(Map<String, Object> item:maps){
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("dt").toString());
                rowData.add(item.get("earnname").toString());
                rowData.add(item.get("bgPVs").toString());
                rowData.add(item.get("bgUVs").toString());
                rowData.add(item.get("djPVs").toString());
                rowData.add(item.get("djUVs").toString());
                rowData.add(item.get("azPVs").toString());
                rowData.add(item.get("azUVs").toString());
                data.add(rowData);
            }
            ExportExcelUtil.export(response,name,titles,data);
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    @RequestMapping("/getCollectDataPageCnt")
    @ResponseBody
    public ResultJson<Integer> getCollectDataPageCnt(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        String version = request.getParameter("version");
        paramMap.put("version", version);
        return zAdService.getCollectDataPageCnt(paramMap);
    }

    @RequestMapping("/getCollectDataByPage")
    @ResponseBody
    public ResultJson<List<Map<String, Object>>> getCollectDataByPage(HttpServletRequest request){
        Map<String, Object> paramMap = handleParams(request);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        paramMap.put("pageNo", pageNo);
        return zAdService.getCollectDataByPage(paramMap);
    }


    /**
     * 导出SDK广告数据汇总
     * @param request
     * @return
     */
    @RequestMapping("/exportCollectData")
    public void exportCollectData(HttpServletRequest request,HttpServletResponse response) {
        List<Map<String, Object>> maps = null;
        try {
            Map<String, Object> paramMap = handleParams(request);
            String adType = request.getParameter("adType");
            paramMap.put("adType", adType);
            maps = zAdService.exportCollectData(paramMap);
            String name = "SDK广告数据汇总";
            List<String> titles = new ArrayList<>();
            titles.add("日期");
            titles.add("曝光PV");
            titles.add("曝光UV");
            titles.add("点击PV");
            titles.add("点击UV");
            List<List<Object>> data = new ArrayList<>();
            for (Map<String, Object> item : maps) {
                List<Object> rowData = new ArrayList<>();
                rowData.add(item.get("dt").toString());
                rowData.add(item.get("bgpv").toString());
                rowData.add(item.get("bguv").toString());
                rowData.add(item.get("djpv").toString());
                rowData.add(item.get("djuv").toString());
                data.add(rowData);
            }
            ExportExcelUtil.export(response, name, titles, data);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

}
