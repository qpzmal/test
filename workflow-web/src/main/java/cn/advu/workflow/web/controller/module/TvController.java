package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseTv;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 媒体分类相关controller，用于管理行业
 *
 */
@Controller
@RequestMapping("/tv")
public class TvController {
    
    @Autowired
    private TvService tvService;

    /**
     * 跳转媒体分类业务首页-行业列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseTv>> result = tvService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "modules/tv/list";
    }

    /**
     * 新增媒体分类
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addTv(BaseTv baseTv, HttpServletRequest request){

        return tvService.addTv(baseTv);
    }

    /**
     * 跳转新增媒体分类页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "modules/tv/add";
    }

    /**
     * 跳转到更新媒体分类页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseTv baseTv = tvService.findById(id).getData();

        model.addAttribute("baseTv", baseTv);

        return "modules/tv/update";
    }
}
