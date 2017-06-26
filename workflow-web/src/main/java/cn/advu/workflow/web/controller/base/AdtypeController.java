package cn.advu.workflow.web.controller.base;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.AdtypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 媒体相关controller，用于管理行业
 *
 */
@Controller
@RequestMapping("/adtype")
public class AdtypeController {
    
    @Autowired
    private AdtypeService adtypeService;

    /**
     * 跳转行业业务首页-行业列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseAdtype>> result = adtypeService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "adtype/index_list";
    }

    /**
     * 新增行业
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addAdtype(BaseAdtype baseAdtype, HttpServletRequest request){
        return adtypeService.addAdtype(baseAdtype);
    }

    /**
     * 跳转新增行业页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "adtype/add";
    }

}
