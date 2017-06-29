package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseCustom;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.CustomService;
import cn.advu.workflow.web.service.base.IndustryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 客户相关controller，用于管理客户
 *
 */
@Controller
@RequestMapping("/custom")
public class CustomController {

    @Autowired
    private CustomService customService;

    @Autowired
    private IndustryService industryService;

    /**
     * 跳转客户首页-客户列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseCustom>> result = customService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "modules/custom/list";
    }

    /**
     * 新增客户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseCustom baseCustom, HttpServletRequest request){
        return customService.add(baseCustom);
    }

    /**
     * 更新客户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateArea(BaseCustom baseCustom, HttpServletRequest request){
        return customService.update(baseCustom);
    }

    /**
     * 跳转新增客户页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model resultModel){
        List<BaseIndustry> industryList = industryService.findAll().getData();
        resultModel.addAttribute("industryList", industryList);
        return "modules/custom/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseCustom baseCustom = customService.findById(id).getData();

        model.addAttribute("baseCustom", baseCustom);

        List<BaseIndustry> industryList = industryService.findAll().getData();
        model.addAttribute("industryList", industryList);

        return "modules/custom/update";
    }


}
