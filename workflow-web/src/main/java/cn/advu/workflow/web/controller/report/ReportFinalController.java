package cn.advu.workflow.web.controller.report;

import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.datareport.BaseExecuteOrderReportVO;
import cn.advu.workflow.web.manager.ExecuteOrderManager;
import cn.advu.workflow.web.manager.FinancialindexMananger;
import cn.advu.workflow.web.manager.MediaMananger;
import cn.advu.workflow.web.util.BigDecimalUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by wangry on 17/8/19.
 */
@Controller
@RequestMapping("/report/finalReport")
public class ReportFinalController {

    private static Logger LOGGER = LoggerFactory.getLogger(ReportFinalController.class);

    @Autowired
    ExecuteOrderManager executeOrderManager;
    @Autowired
    MediaMananger mediaMananger;

    @Autowired
    FinancialindexMananger financialindexMananger;

    @RequestMapping("/")
    public String finalReport(Model resultModel){
        return "report/final_report";
    }

    @RequestMapping("/title")
    @ResponseBody
    public JSONObject finalReportTitle() {
        JSONArray totalTile = new JSONArray();
        setFixedTitle(totalTile);
        List<BaseMedia> baseMediaList = mediaMananger.findAllActiveMedia();
        JSONObject result = new JSONObject();
        result.put("data", totalTile);
        return result;
    }

    private void setFixedTitle(JSONArray totalTile) {

        JSONObject fixedTitle = new JSONObject();
        fixedTitle.put("field", "orderNum");
        fixedTitle.put("title", "编号");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "customSign");
        fixedTitle.put("title", "4A公司/直客");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "name");
        fixedTitle.put("title", "项目");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "personLeader");
        fixedTitle.put("title", "项目负责人");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "deliveryAreaNames");
        fixedTitle.put("title", "地区");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "startDate");
        fixedTitle.put("title", "开始日期");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "endDate");
        fixedTitle.put("title", "截止日期");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "adTypeName");
        fixedTitle.put("title", "形式");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "taxAmount");
        fixedTitle.put("title", "订单金额");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "rebate");
        fixedTitle.put("title", "返点");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "taxIncome");
        fixedTitle.put("title", "含税收入");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "netIncome");
        fixedTitle.put("title", "净收入");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "totalCpmNum");
        fixedTitle.put("title", "总CPM量");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "totalCost");
        fixedTitle.put("title", "总成本");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "grossProfit");
        fixedTitle.put("title", "毛利");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "grossProfitRate");
        fixedTitle.put("title", "毛利率(%)");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);



    }

    @RequestMapping("/list")
    @ResponseBody
    public JSONObject list(String likeSearch, Model resultModel){

        List<BaseExecuteOrderReportVO> baseExecuteOrderList = executeOrderManager.findFinalReport(likeSearch);
        JSONArray baseExecuteOrderVOList = new JSONArray();
        if (baseExecuteOrderList != null && !baseExecuteOrderList.isEmpty()) {
            for (BaseExecuteOrderReportVO baseExecuteOrderReportVO : baseExecuteOrderList) {
                JSONObject baseExecuteOrderVO = new JSONObject();
                baseExecuteOrderVO.put("orderNum", baseExecuteOrderReportVO.getOrderNum());
                baseExecuteOrderVO.put("customSign", baseExecuteOrderReportVO.getCustomSignName());
                baseExecuteOrderVO.put("name", baseExecuteOrderReportVO.getName());
                baseExecuteOrderVO.put("personLeader", baseExecuteOrderReportVO.getPersonLeaderName());
                baseExecuteOrderVO.put("deliveryAreaNames", baseExecuteOrderReportVO.getDeliveryAreaNames());
                baseExecuteOrderVO.put("startDate", baseExecuteOrderReportVO.getStartDate());
                baseExecuteOrderVO.put("endDate", baseExecuteOrderReportVO.getEndDate());
                baseExecuteOrderVO.put("adTypeName", baseExecuteOrderReportVO.getAdTypeName());
                BigDecimal taxAmount = baseExecuteOrderReportVO.getTaxAmount();
                BigDecimal rebate = baseExecuteOrderReportVO.getPublicRebate();

                baseExecuteOrderVO.put("taxAmount", taxAmount);
                baseExecuteOrderVO.put("rebate", rebate);
                // 含税收入＝订单金额＊（1-返点）
                BigDecimal taxIncome = BigDecimal.ONE.subtract(rebate).multiply(taxAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                // 净收入＝含税收入／1.06
                BigDecimal netIncome = taxIncome.divide(financialindexMananger.getMarkUpRate(), 2, BigDecimal.ROUND_HALF_UP);

                baseExecuteOrderVO.put("taxIncome", taxIncome);
                baseExecuteOrderVO.put("netIncome", netIncome);

                baseExecuteOrderVO.put("totalCpmNum", baseExecuteOrderReportVO.getTotalCpmCount());

                BigDecimal totalCost = baseExecuteOrderReportVO.getTotalCpmCost();
                baseExecuteOrderVO.put("totalCost", totalCost);

                BigDecimal grossProfit = netIncome.subtract(totalCost);
                baseExecuteOrderVO.put("grossProfit", grossProfit);

                String grossProfitRateString = null;
                if (netIncome.doubleValue() != 0) {
                    BigDecimal grossProfitRate = grossProfit.divide(netIncome, 4, BigDecimal.ROUND_HALF_UP);
                    grossProfitRateString = grossProfitRate.multiply(BigDecimalUtil.HUNDRED).stripTrailingZeros().toPlainString() + "%";
                }
                baseExecuteOrderVO.put("grossProfitRate", grossProfitRateString);

                baseExecuteOrderVOList.add(baseExecuteOrderVO);
            }
        }
        JSONObject sysLogVO = new JSONObject();
        sysLogVO.put("rows", baseExecuteOrderVOList);

        resultModel.addAttribute("dataJsonStr", sysLogVO);
        return sysLogVO;
    }

}
