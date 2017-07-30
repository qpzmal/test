package cn.advu.workflow.web.controller.demand;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.web.manager.AdtypeMananger;
import cn.advu.workflow.web.manager.CpmManager;
import cn.advu.workflow.web.manager.MediaMananger;
import cn.advu.workflow.web.util.AssertUtil;
import cn.advu.workflow.web.util.BigDecimalUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/salePrice")
public class SalePriceController {

    @Autowired
    MediaMananger mediaMananger;

    @RequestMapping("/addCounter")
    public String addAcounter(Model resultModel){

        // find cpmList
        List<BaseMedia> baseMediaList = mediaMananger.findAllActiveMedia();
        resultModel.addAttribute("baseMediaList", baseMediaList);
        resultModel.addAttribute("num", System.currentTimeMillis());

        return "demand/counter/addCounter";
    }


    @RequestMapping("/totalUpdate")
    public String totalUpdate(String json, String jsonBefore, Model resultModel){
        JSONArray inputParamList = JSONArray.parseArray(json);
        JSONArray updateBeforeList = JSONArray.parseArray(jsonBefore);
        for (int inputParamIndex = 0; inputParamIndex <  inputParamList.size(); inputParamIndex++) {
            JSONObject inputParam = (JSONObject) inputParamList.get(inputParamIndex);
            String inputNum =inputParam.getString("num");

            for (int updateBeforeIndex = 0; updateBeforeIndex <  updateBeforeList.size(); updateBeforeIndex++) {
                JSONObject inputParamBefore = (JSONObject) updateBeforeList.get(updateBeforeIndex);
                String beforeNum  =inputParamBefore.getString("num");
                // 如果存在则更新销售数量
                if (beforeNum != null && beforeNum.equals(inputNum)) {
                    inputParam.put("saleCount", inputParamBefore.getBigDecimal("saleCount"));
                    break;
                }
            }
        }

        resetInputParamList(inputParamList);
        resultModel.addAttribute("inputParamList", inputParamList);
        resultModel.addAttribute("std", BigDecimalUtil.HUNDRED);

        return "demand/counter/total";
    }

    @RequestMapping("/totalInfo")
    public String total(String json, Model resultModel){

        JSONArray inputParamList = JSONArray.parseArray(json);

        resetInputParamList(inputParamList);
        resultModel.addAttribute("inputParamList", inputParamList);

        resultModel.addAttribute("std", BigDecimalUtil.HUNDRED);

        return "demand/counter/total";
    }

    private void resetInputParamList(JSONArray inputParamList) {
        BigDecimal saleCountSum = BigDecimal.ZERO;
        BigDecimal netIncomeSum = BigDecimal.ZERO;
        BigDecimal netProfitSum = BigDecimal.ZERO;
        BigDecimal netIncomePriceSum = BigDecimal.ZERO;

        int index = 1;
        for (Object rowInpuParam : inputParamList) {
            JSONObject rowInputJsonObject = (JSONObject)rowInpuParam;

            BigDecimal netIncomePrice = BigDecimalUtil.getBigDecimalWithDefaultZero(rowInputJsonObject.getBigDecimal("netIncomePrice"));
            BigDecimal netProfitRate = BigDecimalUtil.getBigDecimalWithDefaultZero(rowInputJsonObject.getBigDecimal("netProfitRate"));
            String adtypeName = rowInputJsonObject.getString("adtypeName");
            BigDecimal saleCount = BigDecimalUtil.getBigDecimalWithDefaultZero(rowInputJsonObject.getBigDecimal("saleCount"));

            BigDecimal netIncome = netIncomePrice.multiply(saleCount).setScale(2, BigDecimal.ROUND_UP);
            BigDecimal netProfit = netIncome.multiply(netProfitRate).setScale(2, BigDecimal.ROUND_UP);
            rowInputJsonObject.put("netIncome", netIncome);
            rowInputJsonObject.put("netProfit", netProfit);

            saleCountSum = saleCountSum.add(saleCount);
            netIncomeSum = netIncomeSum.add(netIncome);
            netProfitSum = netProfitSum.add(netProfit);
            netIncomePriceSum = netIncomePriceSum.add(netIncomePrice);

            rowInputJsonObject.put("idNum",  index++);
        }
        if (inputParamList != null && !inputParamList.isEmpty()) {
            JSONObject sum = new JSONObject();
            sum.put("adtypeName", "综合");
            sum.put("saleCount", saleCountSum);
            sum.put("netIncome", netIncomeSum);
            sum.put("netProfit", netProfitSum);
            sum.put("netIncomePrice", netIncomePriceSum.divide(new BigDecimal(inputParamList.size()), 2, BigDecimal.ROUND_UP));
            if(netIncomeSum.compareTo(BigDecimal.ZERO) != 0) {
                sum.put("netProfitRate", netProfitSum.divide(netIncomeSum, 2, BigDecimal.ROUND_UP));
            } else {
                sum.put("netProfitRate", BigDecimal.ZERO);
            }
            sum.put("idNum",  index++);
            inputParamList.add(sum);
        }

    }

    @RequestMapping("/index")
    public String toIndex(Model resultModel){

        return "demand/counter/salePriceCounter";
    }

    @RequestMapping("/accoutInfo")
    public String accoutInfo(SalePriceAccoutVO salePriceAccoutVO, Model resultModel){

        BigDecimal mediaPrice = salePriceAccoutVO.getMediaPrice();
        BigDecimal publicRebate = BigDecimalUtil.getBigDecimalWithDefaultZero(salePriceAccoutVO.getPublicRebate());
        BigDecimal purchase = BigDecimalUtil.getBigDecimalWithDefaultZero(salePriceAccoutVO.getPurchase());
        BigDecimal salesIncentiveRate = BigDecimalUtil.getBigDecimalWithDefaultZero(salePriceAccoutVO.getSalesIncentiveRate());
        AssertUtil.assertNotNull(mediaPrice);
        AssertUtil.assertNotNull(purchase);

        // 净收入＝（1-对公返点）＊媒体单价／1.06
        BigDecimal netIncome = BigDecimal.ONE.subtract(publicRebate).multiply(mediaPrice).divide(new BigDecimal("1.06"), 2, BigDecimal.ROUND_UP);
        // 主营业务税金及附加＝（净收入－媒体采购成本）＊0.06*0.12
        BigDecimal bizTax = netIncome.subtract(purchase).multiply(new BigDecimal("0.06")).multiply(new BigDecimal("0.12")).setScale(2, BigDecimal.ROUND_UP);
        // 文化建设税金＝（净收入－媒体采购成本）＊1.06*0.03
        BigDecimal cultureRate = netIncome.subtract(purchase).multiply(new BigDecimal("1.06")).multiply(new BigDecimal("0.03")).setScale(2, BigDecimal.ROUND_UP);
        // 税金总计＝主营业务税金及附加＋文化建设税金
        BigDecimal totalTax = bizTax.add(cultureRate);
        // 毛利＝净收入－媒体采购成本－税金总计
        BigDecimal grossProfit = netIncome.subtract(purchase).subtract(totalTax);
        // 毛利率＝毛利／净收入
        BigDecimal grossProfitRate = grossProfit.divide(netIncome, 2, BigDecimal.ROUND_UP);
        // 销售提成比例＝0.035
        BigDecimal salesCommissionsRate = new BigDecimal("0.035");
        // 销售提成金额＝媒体单价＊销售提成比例
        BigDecimal salesCommissions = mediaPrice.multiply(salesCommissionsRate);
        salesCommissions.setScale(2, BigDecimal.ROUND_UP);

        // 工资房租分摊比例＝0.115
        BigDecimal salaryRentRate = new BigDecimal("0.115");

        // 工资房租分摊额＝媒体单价＊工资房租分摊比例
        BigDecimal salaryRent =mediaPrice.multiply(salaryRentRate);
        salaryRent.setScale(2, BigDecimal.ROUND_UP);

        // 销售激励金额＝媒体单价＊销售激励比例
        BigDecimal salesIncentive = mediaPrice.multiply(salesIncentiveRate);
        salesIncentive.setScale(2, BigDecimal.ROUND_UP);
        // 税前净利＝毛利－销售提成金额－工资房租分摊额－销售激励金额
        BigDecimal preTaxNetProfit = grossProfit.subtract(salesCommissions).subtract(salaryRent).subtract(salesIncentive);

        // 所得税率＝0.15
        BigDecimal incomeTaxRate = new BigDecimal("0.15");
        // 所得税＝税前净利＊所得税率
        BigDecimal incomeTax = preTaxNetProfit.multiply(incomeTaxRate).setScale(2, BigDecimal.ROUND_UP);

        // 税后净利＝税前净利－所得税
        BigDecimal afterTaxNetProfit = preTaxNetProfit.subtract(incomeTax);

        // 净利润率＝税后净利／净收入
        BigDecimal netProfitRate = afterTaxNetProfit.divide(netIncome, 2, BigDecimal.ROUND_UP);


        resultModel.addAttribute("mediaPrice", mediaPrice);
        resultModel.addAttribute("publicRebate", publicRebate);
        resultModel.addAttribute("purchase", purchase);
        resultModel.addAttribute("salesIncentiveRate", salesIncentiveRate);

        resultModel.addAttribute("netIncome", netIncome);
        resultModel.addAttribute("bizTax", bizTax);
        resultModel.addAttribute("cultureRate", cultureRate);
        resultModel.addAttribute("totalTax", totalTax);
        resultModel.addAttribute("grossProfit", grossProfit);
        resultModel.addAttribute("grossProfitRate", grossProfitRate);

        resultModel.addAttribute("salesCommissionsRate", salesCommissionsRate);
        resultModel.addAttribute("salesCommissions", salesCommissions);
        resultModel.addAttribute("salaryRentRate", salaryRentRate);
        resultModel.addAttribute("salaryRent", salaryRent);

        resultModel.addAttribute("salesIncentive", salesIncentive);
        resultModel.addAttribute("preTaxNetProfit", preTaxNetProfit);
        resultModel.addAttribute("incomeTaxRate", incomeTaxRate);
        resultModel.addAttribute("incomeTax", incomeTax);
        resultModel.addAttribute("afterTaxNetProfit", afterTaxNetProfit);
        resultModel.addAttribute("netProfitRate", netProfitRate);
        BigDecimal std = new BigDecimal("100");
        resultModel.addAttribute("std", std);

        return "demand/counter/resetCounter";
    }



}
