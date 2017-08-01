package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.common.loginContext.LoginAccount;
import cn.advu.workflow.web.common.loginContext.LoginUser;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.dto.system.UserRole;
import cn.advu.workflow.web.manager.BizLogManager;
import cn.advu.workflow.web.service.system.LoginService;
import cn.advu.workflow.web.service.system.SysRoleService;
import cn.advu.workflow.web.service.system.SysUserService;
import cn.advu.workflow.web.util.AssertUtil;
import cn.advu.workflow.web.vo.MenuVO;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import net.sf.ehcache.search.parser.MCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("log")
public class LogController {

    private static Logger LOGGER = LoggerFactory.getLogger(LogController.class);

    @Autowired
    BizLogManager bizLogManager;

    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        List<SysLogWithBLOBs> bizLogManagerAllLog = bizLogManager.findAllLog(0, 30);

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
        sysLogVO.put("total", bizLogManager.count());
        sysLogVO.put("rows", sysLogVOList);

        resultModel.addAttribute("dataJsonStr", sysLogVO);
        return "system/log/list";
    }

    @RequestMapping("/list")
    public JSONObject list(Integer pageNumber, Integer pageSize, Model resultModel){

        AssertUtil.assertNotNull(pageNumber);
        AssertUtil.assertNotNull(pageSize);

        Integer index = (pageNumber - 1)*pageSize;
        List<SysLogWithBLOBs> bizLogManagerAllLog = bizLogManager.findAllLog(index, pageSize);

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
        sysLogVO.put("total", bizLogManager.count());
        sysLogVO.put("rows", sysLogVOList);

        resultModel.addAttribute("dataJsonStr", sysLogVO);
        return sysLogVO;
    }


}
