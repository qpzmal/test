package cn.advu.workflow.web.controller.demand;

import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
import cn.advu.workflow.domain.fcf_vu.SalePriceAccoutVO;
import cn.advu.workflow.web.manager.CpmManager;
import cn.advu.workflow.web.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;



@Controller
@RequestMapping("/salePrice")
public class SalePriceController {

    @Autowired
    CpmManager cpmManager;

    @RequestMapping("/index")
    public String toIndex(Model resultModel){

        // find cpmList
        List<BaseOrderCpmVO> baseOrderCpmVOList = cpmManager.findPassBuyOrderCpm();
        resultModel.addAttribute("baseOrderCpmVOList", baseOrderCpmVOList);

        return "demand/counter/salePriceCounter";
    }


    @RequestMapping("/cpmInfo")
    public String cpmInfo(Integer cpmId, Model resultModel){

        BaseOrderCpm baseOrderCpm = cpmManager.findById(cpmId);

        resultModel.addAttribute("baseOrderCpm", baseOrderCpm);

        return "demand/counter/cpmInfo";
    }

    @RequestMapping("/accoutInfo")
    public String accoutInfo(SalePriceAccoutVO salePriceAccoutVO, Model resultModel){

        BigDecimal mediaPrice = salePriceAccoutVO.getMediaPrice();
        BigDecimal publicRebate = salePriceAccoutVO.getPublicRebate();
        BigDecimal purchase = salePriceAccoutVO.getPurchase();
        BigDecimal salesIncentiveRate = salePriceAccoutVO.getSalesIncentiveRate();
        AssertUtil.assertNotNull(mediaPrice);
        AssertUtil.assertNotNull(publicRebate);
        AssertUtil.assertNotNull(purchase);
        AssertUtil.assertNotNull(salesIncentiveRate);

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

        return "demand/counter/countInfo";
    }



}
