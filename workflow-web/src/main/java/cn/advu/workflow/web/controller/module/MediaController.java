package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.domain.fcf_vu.BaseMediaType;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.tool.DisplayTool;
import cn.advu.workflow.web.service.base.MediaService;
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
 * 媒体相关controller，用于管理媒体
 *
 */
@Controller
@RequestMapping("/media")
public class MediaController {
    
    @Autowired
    private MediaService mediaService;
    @Autowired
    private MediaTypeService mediaTypeService;

    /**
     * 跳转区域媒体首页-媒体列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){

        List<BaseMediaType> activeMediaList = mediaTypeService.findActiveType().getData();

        ResultJson<List<BaseMedia>> result = mediaService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        resultModel.addAttribute("typeList", activeMediaList);
        DisplayTool.buttonDisplay(resultModel, "add", "10901");
        DisplayTool.buttonDisplay(resultModel, "modify", "10902");
        DisplayTool.buttonDisplay(resultModel, "delete", "10903");
        return "modules/media/list";
    }

    /**
     * 新增媒体
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseMedia baseMedia, HttpServletRequest request){
        return mediaService.addMedia(baseMedia);
    }

    /**
     * 更新媒体
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateArea(BaseMedia baseMedia, HttpServletRequest request){
        return mediaService.updateMedia(baseMedia);
    }

    /**
     * 跳转新增媒体页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model model){
        List<BaseMediaType> activeMediaList = mediaTypeService.findActiveType().getData();
        model.addAttribute("typeList", activeMediaList);
        DisplayTool.buttonDisplay(model, "add", "10901");
        return "modules/media/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        List<BaseMediaType> activeMediaList = mediaTypeService.findActiveType().getData();
        model.addAttribute("typeList", activeMediaList);

        BaseMedia baseMedia = mediaService.findById(id).getData();

        model.addAttribute("typeList", activeMediaList);
        model.addAttribute("baseMedia", baseMedia);
        DisplayTool.buttonDisplay(model, "modify", "10902");

        return "modules/media/update";
    }


}
