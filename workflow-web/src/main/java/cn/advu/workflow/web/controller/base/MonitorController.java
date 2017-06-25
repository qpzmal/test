package cn.advu.workflow.web.controller.base;

import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import cn.advu.workflow.web.service.base.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by weiqz on 2017/6/25.
 */
@Controller
@RequestMapping("monitor")
public class MonitorController {
    private static Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    @Autowired
    private MonitorService monitorService;

    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        List<BaseMonitorRequest> monitorList = monitorService.queryAll();
        resultModel.addAttribute("monitorList",monitorList);
        return "base/monitor_index";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "base/monitor_to_add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){
        return "base/monitor_to_update";
    }

    @RequestMapping("/add")
    public String doAdd(){
        return "";
    }

    @RequestMapping("/update")
    public String doUpdate(){
        return "";
    }
}
