package cn.advu.workflow.web.controller.base;

import cn.advu.workflow.domain.fcf_vu.BaseRegion;
import cn.advu.workflow.web.common.ResultJson;
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
@RequestMapping("/region")
public class RegionController {
    
    @Autowired
    private RegionService regionService;

    /**
     * 跳转区域业务首页-区域列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseRegion>> result = regionService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "region/index_list";
    }

    /**
     * 新增区域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseRegion baseRegion, HttpServletRequest request){
        return regionService.addRegion(baseRegion);
    }

    /**
     * 跳转新增区域页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "region/add";
    }

}