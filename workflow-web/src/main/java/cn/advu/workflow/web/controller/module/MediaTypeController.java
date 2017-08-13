package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.tool.DisplayTool;
import cn.advu.workflow.web.service.base.MediaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 媒体类型相关controller，用于管理媒体类型
 *
 */
@Controller
@RequestMapping("/mediaType")
public class MediaTypeController {
    
    @Autowired
    private MediaTypeService mediaTypeService;

    /**
     * 跳转区域媒体类型首页-媒体类型列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseMediaType>> result = mediaTypeService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        DisplayTool.buttonDisplay(resultModel, "add", "11001");
        DisplayTool.buttonDisplay(resultModel, "modify", "11002");
        DisplayTool.buttonDisplay(resultModel, "delete", "11003");
        return "modules/mediaType/list";
    }

    /**
     * 新增媒体类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseMediaType baseMediaType, HttpServletRequest request){
        return mediaTypeService.add(baseMediaType);
    }

    /**
     * 更新媒体类型
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateArea(BaseMediaType baseMediaType, HttpServletRequest request){
        return mediaTypeService.update(baseMediaType);
    }

    /**
     * 跳转新增媒体类型页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "modules/mediaType/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseMediaType baseMediaType = mediaTypeService.findById(id).getData();

        model.addAttribute("baseMediaType", baseMediaType);
        DisplayTool.buttonDisplay(model, "modify", "11002");

        return "modules/mediaType/update";
    }

    @ResponseBody
    @RequestMapping(value ="/remove", method = RequestMethod.POST)
    public ResultJson<Void> remove(Integer id, HttpServletRequest request){
        return mediaTypeService.remove(id);
    }

}
