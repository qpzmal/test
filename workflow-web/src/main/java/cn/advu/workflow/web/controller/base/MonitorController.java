package cn.advu.workflow.web.controller.base;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weiqz on 2017/6/25.
 */
@Controller
@RequestMapping("monitor")
public class MonitorController {

    @RequestMapping("/index")
    public String toIndexV1(){
        return "base/monitor_index";
    }
}
