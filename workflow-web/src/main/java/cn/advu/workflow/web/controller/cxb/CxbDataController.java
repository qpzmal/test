package cn.advu.workflow.web.controller.cxb;

import cn.advu.workflow.web.service.CxbDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/cxb")
public class CxbDataController {
    @Autowired
    private CxbDataService cdataService;
    
    @RequestMapping(value = "/getVersions", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getProductVersions(){
        return cdataService.getVersions();
    }
}
