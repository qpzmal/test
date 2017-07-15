package cn.advu.workflow.web.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by weiqz on 2017/7/13.
 */
@Controller
@RequestMapping("/report/area")
public class ReportAreaController {

    /**
     * 损益分析
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/balance")
    public String balance(Model resultModel, String type){
        return "report/area_index";
    }

    /**
     * 预算完成度分析
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/budget")
    public String budget(Model resultModel, String type){
        return "report/budget_index";
    }













    /**
     * 跳转到报表模版页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel, String type){
//        ReportTypeEnum customTypeEnum = ValueEnumUtils.getEnum(ReportTypeEnum.class, type);
//        switch(customTypeEnum) {
//            case SALE_HISTORY:
//                resultModel.addAttribute("uri", "/report/sale/history");
//                break;
//            case SALE_FEATURE:
//                resultModel.addAttribute("uri", "/report/sale/feature");
//                break;
//            case BUY_RESOURCE:
//                resultModel.addAttribute("uri", "/report/buy/resource");
//                break;
//            case BUY_AREA:
//                resultModel.addAttribute("uri", "/report/buy/area");
//                break;
//            case AREA_BALANCE:
//                resultModel.addAttribute("uri", "/report/area/balance");
//                break;
//            case AREA_BUDGET:
//                resultModel.addAttribute("uri", "/report/area/budget");
//                break;
//            case CUSTOMER_PROFIT:
//                resultModel.addAttribute("uri", "/report/customer/profit");
//                break;
//            case CUSTOMER_PAY:
//                resultModel.addAttribute("uri", "/report/customer/pay");
//                break;
//            default:
//                break;
//        }

        return "report/area_index";
    }
}
