package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.SysLog;
import cn.advu.workflow.domain.fcf_vu.SysLogWithBLOBs;
import cn.advu.workflow.domain.golbal.Page;
import cn.advu.workflow.domain.utils.PageUtil;
import cn.advu.workflow.web.common.RequestUtil;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.util.AssertUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class LogController {

    private static Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @Autowired
    BizLogManager bizLogManager;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/index")
    public String toIndex(Model resultModel, SysLog queryParam){
//        String startDate = RequestUtil.getStringParamDef(request, "startDate", "");
//        String endDate = RequestUtil.getStringParamDef(request, "endDate", "");
//        Page page = PageUtil.initPage(1, 30);
//        queryParam.setPage(page);
//
//        List<SysLogWithBLOBs> bizLogManagerAllLog = bizLogManager.findAllLog(startDate, endDate, queryParam);
//
//        JSONArray sysLogVOList = new JSONArray();
//        if (bizLogManagerAllLog != null && !bizLogManagerAllLog.isEmpty()) {
//            for (SysLogWithBLOBs sysLog : bizLogManagerAllLog) {
//                JSONObject sysLogVO = new JSONObject();
//                String content = sysLog.getContent();
//                sysLogVO.put("content", content);
//                sysLogVO.put("operation", sysLog.getOperation());
//                sysLogVO.put("operator", sysLog.getOperator());
////                sysLogVO.put("type", sysLog.getType().toString());
//                sysLogVOList.add(sysLogVO);
//            }
//        }
//
//        JSONObject sysLogVO = new JSONObject();
//        sysLogVO.put("total", bizLogManager.count(startDate, endDate, queryParam));
//        sysLogVO.put("rows", sysLogVOList);
//        sysLogVO.put("pageSize", page.getPageSize());
//
//        resultModel.addAttribute("dataJsonStr", sysLogVO);
        return "system/log/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public JSONObject list(Integer pageNumber, Integer pageSize, SysLog queryParam, Model resultModel){
        String startDate = RequestUtil.getStringParamDef(request, "startDate", "");
        String endDate = RequestUtil.getStringParamDef(request, "endDate", "");

        AssertUtil.assertNotNull(pageNumber);
        AssertUtil.assertNotNull(pageSize);

        Page page = PageUtil.initPage(pageNumber, pageSize);
        queryParam.setPage(page);

        List<SysLogWithBLOBs> bizLogManagerAllLog = bizLogManager.findAllLog(startDate, endDate, queryParam);

        JSONArray sysLogVOList = new JSONArray();
        if (bizLogManagerAllLog != null && !bizLogManagerAllLog.isEmpty()) {
            for (SysLogWithBLOBs sysLog : bizLogManagerAllLog) {
                JSONObject sysLogVO = new JSONObject();
                String content = sysLog.getContent();
                sysLogVO.put("content", content);
                sysLogVO.put("operation", sysLog.getOperation());
                sysLogVO.put("operator", sysLog.getOperator());
//                sysLogVO.put("type", sysLog.getType().toString());
                sysLogVOList.add(sysLogVO);
            }
        }
        JSONObject sysLogVO = new JSONObject();
        sysLogVO.put("total", bizLogManager.count(startDate, endDate, queryParam));
        sysLogVO.put("rows", sysLogVOList);

        resultModel.addAttribute("dataJsonStr", sysLogVO);
        return sysLogVO;
    }


}
