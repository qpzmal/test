package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseCustomFinance;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.manager.TreeMananger;
import cn.advu.workflow.web.service.base.AreaFinanceService;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.CustomFinanceService;
import cn.advu.workflow.web.service.base.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 区域财务结算相关controller，用于管理区域财务结算
 *
 */
@Controller
@RequestMapping("/customFinance")
public class CustomFinanceController {
    
    @Autowired
    private CustomFinanceService customFinanceService;

    @Autowired
    private TreeMananger treeMananger;

    @Autowired
    private CustomService customService;

    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 跳转区域业务首页-区域列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/list")
    public String toList(Integer customId, Model resultModel){

        List<BaseCustomFinance> dataList = customFinanceService.findByCustom(customId).getData();
        resultModel.addAttribute("dataList", dataList);
        resultModel.addAttribute("format", format);

        return "modules/customFinance/customFinanceList";
    }

    /**
     * 跳转区域业务首页-区域列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Integer customId, Model resultModel){

        List<BaseCustomFinance> dataList = customFinanceService.findByCustom(customId).getData();
        List<BaseCustom> customList = customService.findAll().getData();

        resultModel.addAttribute("dataList", dataList);
        resultModel.addAttribute("customList", customList);
        resultModel.addAttribute("customId", customId);
        resultModel.addAttribute("format", format);

        return "modules/customFinance/list";
    }

    /**
     * 新增地域结算
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> add(BaseCustomFinance baseCustomFinance, HttpServletRequest request){
        return customFinanceService.add(baseCustomFinance);
    }

    /**
     * 更新地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Void> update(BaseCustomFinance baseCustomFinance, HttpServletRequest request){
        return customFinanceService.update(baseCustomFinance);
    }

    /**
     * 更新地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/remove", method = RequestMethod.POST)
    public ResultJson<Void> update(Integer id, HttpServletRequest request){
        return customFinanceService.remove(id);
    }

    /**
     * 跳转新增地域页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Integer customId, Model model){

        List<BaseCustom> customList = customService.findAll().getData();

        model.addAttribute("customId", customId);
        model.addAttribute("customList", customList);

        return "modules/customFinance/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseCustomFinance baseCustomFinance = customFinanceService.findById(id).getData();
        List<BaseCustom> customList = customService.findAll().getData();

        model.addAttribute("baseCustomFinance", baseCustomFinance);
        model.addAttribute("customList", customList);

        return "modules/customFinance/update";
    }


}
