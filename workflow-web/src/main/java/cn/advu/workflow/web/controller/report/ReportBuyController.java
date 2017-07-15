package cn.advu.workflow.web.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weiqz on 2017/7/13.
 */
@Controller
@RequestMapping("/report/buy")
public class ReportBuyController {

    /**
     * 资源类别
     *
     * @return
     */
    @RequestMapping("/resource")
    public String resource(){
        return "report/buy_index";
    }
    /**
     * 厂商
     *
     * @return
     */
    @RequestMapping("/area")
    public String area(){
        return "report/buy_index";
    }
}
