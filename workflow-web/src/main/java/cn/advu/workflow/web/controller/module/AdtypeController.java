package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.tool.DisplayTool;
import cn.advu.workflow.web.service.base.AdtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 广告类型相关controller，用于管理广告类型
 *
 */
@Controller
@RequestMapping("/adtype")
public class AdtypeController {
    
    @Autowired
    private AdtypeService adtypeService;

    /**
     * 跳转广告类型业务首页-广告类型列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseAdtype>> result = adtypeService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        DisplayTool.buttonDisplay(resultModel, "add", "10101");
        DisplayTool.buttonDisplay(resultModel, "modify", "10102");
        DisplayTool.buttonDisplay(resultModel, "delete", "10103");
        return "modules/adtype/list";
    }

    /**
     * 新增广告类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addAdtype(BaseAdtype baseAdtype, HttpServletRequest request){
        return adtypeService.addAdtype(baseAdtype);
    }

    /**
     * 更新广告类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateAdtype(BaseAdtype baseAdtype, HttpServletRequest request){
        return adtypeService.udpateAdtype(baseAdtype);
    }

    /**
     * 更新广告类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
    public ResultJson<Void> remove(@PathVariable(value="id") Integer id, HttpServletRequest request){
        return adtypeService.remove(id);
    }

    /**
     * 跳转新增广告类型页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "modules/adtype/add";
    }

    /**
     *
     * 跳转至更新广告类型页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseAdtype baseAdtype = adtypeService.findById(id).getData();

        model.addAttribute("baseAdtype", baseAdtype);
        DisplayTool.buttonDisplay(model, "modify", "10102");

        return "modules/adtype/update";
    }

}
