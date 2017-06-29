package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseMedia;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.MediaService;
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

    /**
     * 跳转区域媒体首页-媒体列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseMedia>> result = mediaService.findAll();
        resultModel.addAttribute("dataList",result.getData());
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
    public String toAdd(){
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

        BaseMedia baseMedia = mediaService.findById(id).getData();

        model.addAttribute("baseMedia", baseMedia);

        return "modules/media/update";
    }


}
