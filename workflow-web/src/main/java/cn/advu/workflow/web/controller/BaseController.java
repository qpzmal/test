package cn.advu.workflow.web.controller;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {
    /**
     * 页面参数处理 
     * @param request
     * @return
     */
    public Map<String, Object> handleParams(HttpServletRequest request){
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String cnidsStr = request.getParameter("cnids");
        String version = request.getParameter("version");
        
        String[] cnids = null;
        //获得并处理填写的渠道号
        if(StringUtils.isNotBlank(cnidsStr)){
            cnids = cnidsStr.split("\\+");
        }
        
        if("-1".equals(version)){
            version = "";
        }
        Map<String, Object> paramMap = new HashMap<>();
        if(request.getParameter("timeCycle") != null){
            Integer timeCycle = Integer.valueOf(request.getParameter("timeCycle"));
            paramMap.put("timeCycle", timeCycle);
        }
        paramMap.put("startTime", startTime);
        paramMap.put("endTime", endTime);
        paramMap.put("cnids", cnids);
        paramMap.put("version", version);
        return paramMap;        
    }
}
