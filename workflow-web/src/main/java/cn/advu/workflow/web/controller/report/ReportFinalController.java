package cn.advu.workflow.web.controller.report;

import cn.advu.workflow.domain.fcf_vu.BaseMedia;
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
import java.text.SimpleDateFormat;
import java.util.*;

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

    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("/")
    public String finalReport(Model resultModel, String likeSearch){
        resultModel.addAttribute("likeSearch", likeSearch);
        return "report/final_report";
    }

    @RequestMapping("/title")
    @ResponseBody
    public JSONObject finalReportTitle() {
        JSONArray totalTile = new JSONArray();
        setFixedTitle(totalTile);
        List<BaseMedia> baseMediaList = mediaMananger.findAllActiveMedia();
        if (baseMediaList != null && !baseMediaList.isEmpty()) {
            for (BaseMedia baseMedia : baseMediaList) {
                JSONObject extendTitle = new JSONObject();
                extendTitle.put("field", "s_"+baseMedia.getId());
                extendTitle.put("title", baseMedia.getName() + "CPM量");
                extendTitle.put("align", "center");
                totalTile.add(extendTitle);
            }
        }
        JSONObject fixedTitle = new JSONObject();
        fixedTitle.put("field", "totalCpmNum");
        fixedTitle.put("title", "总CPM量");
        fixedTitle.put("align", "center");
        totalTile.add(fixedTitle);

        fixedTitle = new JSONObject();
        fixedTitle.put("field", "weightingAverageCost");
        fixedTitle.put("title", "加权平均成本");
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
        fixedTitle.put("title", "返点(%)");
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

    }

    @RequestMapping("/list")
    @ResponseBody
    public JSONObject list(String likeSearch, Model resultModel){

        List<BaseMedia> baseMediaList = mediaMananger.findAllActiveMedia();
        List<String> mediaIdList = null;
        Map<String, BigDecimal> mediaCpmNum = null;
        if (baseMediaList != null && !baseMediaList.isEmpty()) {
            mediaIdList = new ArrayList<>();
            mediaCpmNum = new HashMap<>();
            for (BaseMedia baseMedia : baseMediaList) {
                mediaIdList.add(baseMedia.getId().toString());
                mediaCpmNum.put("s_"+baseMedia.getId().toString(), BigDecimal.ZERO);
            }
        }

        List<Map> baseExecuteOrderList = executeOrderManager.findFinalReport(likeSearch, mediaIdList);
        JSONArray baseExecuteOrderVOList = new JSONArray();
        if (baseExecuteOrderList != null && !baseExecuteOrderList.isEmpty()) {
            BigDecimal totalTaxAmount = BigDecimal.ZERO;
            BigDecimal totalTotalCpmNum = BigDecimal.ZERO;
            BigDecimal totalTotalCpmCost = BigDecimal.ZERO;
            BigDecimal totalGrossProfit = BigDecimal.ZERO;
            for (Map baseExecuteOrderReportVO : baseExecuteOrderList) {
                JSONObject baseExecuteOrderVO = new JSONObject();
                baseExecuteOrderVO.put("orderNum", baseExecuteOrderReportVO.get("orderNum"));
                baseExecuteOrderVO.put("customSign", baseExecuteOrderReportVO.get("customSignName"));
                baseExecuteOrderVO.put("name", baseExecuteOrderReportVO.get("name"));
                baseExecuteOrderVO.put("personLeader", baseExecuteOrderReportVO.get("personLeaderName"));
                baseExecuteOrderVO.put("deliveryAreaNames", baseExecuteOrderReportVO.get("deliveryAreaNames"));
                baseExecuteOrderVO.put("startDate", format.format(baseExecuteOrderReportVO.get("startDate")));
                baseExecuteOrderVO.put("endDate", format.format(baseExecuteOrderReportVO.get("endDate")));
                baseExecuteOrderVO.put("adTypeName", baseExecuteOrderReportVO.get("adTypeName"));
                BigDecimal taxAmount = (BigDecimal)baseExecuteOrderReportVO.get("amount");
                BigDecimal rebate = (BigDecimal)baseExecuteOrderReportVO.get("publicRebate");

                baseExecuteOrderVO.put("taxAmount", taxAmount);
                baseExecuteOrderVO.put("rebate", rebate.multiply(BigDecimalUtil.HUNDRED).stripTrailingZeros().toPlainString() + "%");
                // 含税收入＝订单金额＊（1-返点）
                BigDecimal taxIncome = BigDecimal.ONE.subtract(rebate).multiply(taxAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
                // 净收入＝含税收入／1.06
                BigDecimal netIncome = taxIncome.divide(financialindexMananger.getMarkUpRate(), 2, BigDecimal.ROUND_HALF_UP);

                baseExecuteOrderVO.put("taxIncome", taxIncome);
                baseExecuteOrderVO.put("netIncome", netIncome);


                LOGGER.debug("totalCpmCount:{}", baseExecuteOrderReportVO.get("totalCpmCount"));
                BigDecimal totalCpmNum = BigDecimal.valueOf(Long.valueOf(baseExecuteOrderReportVO.get("totalCpmCount").toString()));
                baseExecuteOrderVO.put("totalCpmNum", totalCpmNum);

                BigDecimal weightingAverageCost = new BigDecimal(0);
                Set<Map.Entry<String, Object>> entrySet = baseExecuteOrderReportVO.entrySet();
                for (Map.Entry<String, Object> e : entrySet) {
                    String name = e.getKey();
                    String value = e.getValue().toString();
                    if(name.startsWith("weightingAverageCost")){
                        LOGGER.debug("weightingAverageCost-value:{}", value);
                        weightingAverageCost = weightingAverageCost.add(BigDecimal.valueOf(Double.valueOf(value)));
                        LOGGER.debug("weightingAverageCost:{}", weightingAverageCost);
                    } else {
                        continue;
                    }
                }
                baseExecuteOrderVO.put("weightingAverageCost", weightingAverageCost);

                BigDecimal totalCost = (BigDecimal)baseExecuteOrderReportVO.get("totalCpmCost");
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

                totalTaxAmount = totalTaxAmount.add(taxAmount);
                totalTotalCpmNum = totalTotalCpmNum.add(totalCpmNum);
                totalTotalCpmCost = totalTotalCpmCost.add(totalCost);
                totalGrossProfit = totalGrossProfit.add(grossProfit);

                if (mediaCpmNum != null) {
                    for (String mediaKey : mediaCpmNum.keySet()) {
                        BigDecimal mediaTotal = mediaCpmNum.get(mediaKey);
                        Object mediaNumObject = baseExecuteOrderReportVO.get(mediaKey);
                        if (mediaNumObject != null) {
                            BigDecimal mediaNum = BigDecimal.valueOf(Long.valueOf(mediaNumObject.toString()));
                            baseExecuteOrderVO.put(mediaKey, mediaNum);
                            mediaTotal = mediaTotal.add(mediaNum);
                            mediaCpmNum.put(mediaKey, mediaTotal);
                        }
                    }
                }
            }

            JSONObject totalExecuteOrderVO = new JSONObject();
            totalExecuteOrderVO.put("adTypeName", "合计");
            totalExecuteOrderVO.put("taxAmount", totalTaxAmount);
            totalExecuteOrderVO.put("totalCpmNum", totalTotalCpmNum);
            totalExecuteOrderVO.put("totalCost", totalTotalCpmCost);
            totalExecuteOrderVO.put("grossProfit", totalGrossProfit);
            totalExecuteOrderVO.putAll(mediaCpmNum);

            baseExecuteOrderVOList.add(totalExecuteOrderVO);
        }
        JSONObject sysLogVO = new JSONObject();
        sysLogVO.put("rows", baseExecuteOrderVOList);

        resultModel.addAttribute("dataJsonStr", sysLogVO);
        return sysLogVO;
    }

}
