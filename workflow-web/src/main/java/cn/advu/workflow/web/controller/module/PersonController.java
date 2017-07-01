package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.web.service.base.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 部门相关controller，用于管理部门
 *
 */
@Controller
@RequestMapping("/person")
public class PersonController {


    @Autowired
    AreaService areaService;

    @RequestMapping("/index")
    public String toIndex(Model resultModel){
//        ResultJson<List<BaseAdtype>> result = adtypeService.findAll();
//        resultModel.addAttribute("dataList",result.getData());
        return "modules/person/list";
    }

    /**
     * 跳转新增员工页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model resultModel){

        List<BaseArea> areaList = areaService.findAll().getData();
        resultModel.addAttribute("areaList", areaList);

//        List<BaseIndustry> industryList = industryService.findAll().getData();
//        resultModel.addAttribute("industryList", industryList);
        return "modules/person/add";
    }
}
