package cn.advu.workflow.web.controller.demand;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpm;
import cn.advu.workflow.domain.fcf_vu.BaseOrderCpmVO;
import cn.advu.workflow.domain.fcf_vu.SalePriceAccoutVO;
import cn.advu.workflow.domain.searchVO.CustomSearchVO;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.manager.CpmManager;
import cn.advu.workflow.web.service.base.CustomService;
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
        BigDecimal netIncome = BigDecimal.ONE.subtract(publicRebate).multiply(mediaPrice).divide(new BigDecimal("1.06")).setScale(2);
        // 主营业务税金及附加＝（净收入－媒体采购成本）＊0.06*0.12
        BigDecimal bizTax = netIncome.subtract(purchase).multiply(new BigDecimal("0.06")).multiply(new BigDecimal("0.12"));
        // 文化建设税金＝（净收入－媒体采购成本）＊1.06*0.03
        BigDecimal cultureRate = netIncome.subtract(purchase).multiply(new BigDecimal("1.06")).multiply(new BigDecimal("0.03"));
        // 税金总计＝主营业务税金及附加＋文化建设税金
        BigDecimal totalTax = bizTax.add(cultureRate);
        // 毛利＝净收入－媒体采购成本－税金总计
        BigDecimal grossProfit = netIncome.subtract(purchase).subtract(totalTax);
        // 毛利率＝毛利／净收入
        BigDecimal grossProfitRate = grossProfit.divide(netIncome).setScale(2);

        resultModel.addAttribute("netIncome", netIncome);
        resultModel.addAttribute("bizTax", bizTax);
        resultModel.addAttribute("cultureRate", cultureRate);
        resultModel.addAttribute("totalTax", totalTax);
        resultModel.addAttribute("grossProfit", grossProfit);
        resultModel.addAttribute("grossProfitRate", grossProfitRate);

        return "demand/counter/countInfo";
    }



}
