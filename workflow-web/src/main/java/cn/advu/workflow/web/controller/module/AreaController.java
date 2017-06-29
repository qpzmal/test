package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.AreaService;
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
 * 区域相关controller，用于管理区域
 *
 */
@Controller
@RequestMapping("/area")
public class AreaController {
    
    @Autowired
    private AreaService areaService;

    /**
     * 跳转区域业务首页-区域列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseArea>> result = areaService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "modules/area/list";
    }

    /**
     * 新增地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseArea baseArea, HttpServletRequest request){
        return areaService.addArea(baseArea);
    }

    /**
     * 更新地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateArea(BaseArea baseArea, HttpServletRequest request){
        return areaService.updateArea(baseArea);
    }

    /**
     * 跳转新增地域页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "modules/area/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseArea baseArea = areaService.findById(id).getData();

        model.addAttribute("baseArea", baseArea);

        return "modules/area/update";
    }


}
