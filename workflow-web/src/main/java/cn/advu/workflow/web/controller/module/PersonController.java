package cn.advu.workflow.web.controller.module;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 部门相关controller，用于管理部门
 *
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    @RequestMapping("/index")
    public String toIndex(Model resultModel){
//        ResultJson<List<BaseAdtype>> result = adtypeService.findAll();
//        resultModel.addAttribute("dataList",result.getData());
        return "modules/person/list";
    }


}
