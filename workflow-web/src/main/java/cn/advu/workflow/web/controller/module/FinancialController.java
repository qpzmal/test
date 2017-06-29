package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseFinancialindex;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.FinancialindexService;
import cn.advu.workflow.web.service.base.MediaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 财务指标相关controller，用于管理财务指标
 *
 */
@Controller
@RequestMapping("/financial")
public class FinancialController {

    @Autowired
    private FinancialindexService financialindexService;

    /**
     * 跳转财务指标首页-财务指标列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseFinancialindex>> result = financialindexService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "modules/financial/list";
    }

    /**
     * 新增财务指标
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseFinancialindex baseFinancialindex, HttpServletRequest request){
        return financialindexService.add(baseFinancialindex);
    }

    /**
     * 更新财务指标
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateArea(BaseFinancialindex baseFinancialindex, HttpServletRequest request){
        return financialindexService.update(baseFinancialindex);
    }

    /**
     * 跳转新增财务指标页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "modules/financial/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseFinancialindex baseFinancialindex = financialindexService.findById(id).getData();

        model.addAttribute("baseFinancialindex", baseFinancialindex);

        return "modules/financial/update";
    }


}
