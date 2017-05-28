package cn.advu.workflow.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 入口controller, 以后删除
 * @author Niu Qianghong
 *
 */
//TODO 入口开发好之后删除
@Controller
public class EnterController {
    
    @RequestMapping("/index")
    public String toIndex(){
        return "index";
    }    
}
