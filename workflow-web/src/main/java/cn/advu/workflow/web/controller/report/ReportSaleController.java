package cn.advu.workflow.web.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weiqz on 2017/7/13.
 */
@Controller
@RequestMapping("/report/sale")
public class ReportSaleController {

    /**
     * 历史订单
     *
     * @return
     */
    @RequestMapping("/history")
    public String history(){
        return "report/sale_history";
    }

    /**
     * 未来订单
     *
     * @return
     */
    @RequestMapping("/feature")
    public String feature(){
        return "report/sale_feature";
    }
}
