package cn.advu.workflow.web.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weiqz on 2017/7/13.
 */
@Controller
@RequestMapping("/report/customer")
public class ReportCustomerController {

    /**
     * 毛利/净利贡献率
     *
     * @return
     */
    @RequestMapping("/profit")
    public String profit(){
        return "report/customer_index";
    }
    /**
     * 回款汇总
     *
     * @return
     */
    @RequestMapping("/pay")
    public String pay(){
        return "report/customer_index";
    }
}
