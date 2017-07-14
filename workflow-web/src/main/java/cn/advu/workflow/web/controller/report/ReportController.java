package cn.advu.workflow.web.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weiqz on 2017/7/13.
 */
@Controller
@RequestMapping("/report")
public class ReportController {

    /**
     * 跳转到报表模版页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel, String type){
        switch(type) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                break;
            default:
                break;
        }
//        "/report/sale/history";
//        "/report/sale/feature";
//        "/report/buy/resource";
//        "/report/buy/area";
//        "/report/area/balance";
//        "/report/area/budget";
//        "/report/customer/profit";
//        "/report/customer/pay";
        return "report/index";
    }
}
