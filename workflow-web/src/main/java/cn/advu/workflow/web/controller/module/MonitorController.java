package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.MonitorRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by weiqz on 2017/6/25.
 */
@Controller
@RequestMapping("monitor")
public class MonitorController {
    private static Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    @Autowired
    private MonitorRequestService monitorRequestService;

    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        resultModel.addAttribute("dataList",monitorRequestService.findAll().getData());
        return "modules/monitor/list";
    }


    /**
     * 新增行业
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addIndustry(BaseMonitorRequest baseMonitorRequest, HttpServletRequest request){
        return monitorRequestService.addMonitorRequest(baseMonitorRequest);
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "modules/monitor/add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){
        return "base/monitor_to_update";
    }



    @RequestMapping("/update")
    public String doUpdate(){
        return "";
    }
}
