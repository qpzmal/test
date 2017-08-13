package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.tool.DisplayTool;
import cn.advu.workflow.web.service.base.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 地域相关controller，用于管理地域
 *
 */
@Controller
@RequestMapping("/region")
public class RegionController {
    
    @Autowired
    private RegionService regionService;

    /**
     * 跳转地域业务首页-地域列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseRegion>> result = regionService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        DisplayTool.buttonDisplay(resultModel, "add", "10401");
        DisplayTool.buttonDisplay(resultModel, "modify", "10402");
        DisplayTool.buttonDisplay(resultModel, "delete", "10403");
        return "modules/region/list";
    }

    /**
     * 新增地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseRegion baseRegion, HttpServletRequest request){
        return regionService.addRegion(baseRegion);
    }

    /**
     * 更新地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateRegion(BaseRegion baseRegion, HttpServletRequest request){
        return regionService.updateRegion(baseRegion);
    }

    /**
     * 跳转新增地域页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "modules/region/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseRegion baseRegion = regionService.findById(id).getData();

        model.addAttribute("baseRegion", baseRegion);
        DisplayTool.buttonDisplay(model, "modify", "10402");

        return "modules/region/update";
    }

    /**
     * 删除客户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/remove", method = RequestMethod.POST)
    public ResultJson<Void> remove(Integer id, HttpServletRequest request){
        return regionService.remove(id);
    }

}
