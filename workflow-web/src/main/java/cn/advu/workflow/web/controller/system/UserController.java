package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.SysUser;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("user")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysUserService sysUserService;


    @RequestMapping("/toAdd")
    public String toAdd(){
        LOGGER.debug("user-to-add-start");
        return "user/add";
    }

    @RequestMapping("/index_v1")
    public String toIndexV1(){
        return "index_v1";
    }
    
    @RequestMapping("/toList")
    public String toList(){
        return "user/list";
    }
    
    /**
     *
     * 新增用户
     *
     * @param sysUser
     * @param request
     * @return
     */
    @RequestMapping("/")
    @ResponseBody
    public ResultJson<Void> add(SysUser sysUser, HttpServletRequest request){
        return sysUserService.add(sysUser);
    }


}
