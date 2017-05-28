package cn.advu.workflow.web.controller.zwsc;

import cn.advu.workflow.web.service.ZwscDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/zwsc")
public class ZwscDataController {
    
    @Autowired
    private ZwscDataService zdataService;
    
    /**
     * 获取中文书城版本号
     * @return
     */
    @RequestMapping(value = "/getVersions", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getProductVersions(){
        return zdataService.getVersions();
    }  
}
