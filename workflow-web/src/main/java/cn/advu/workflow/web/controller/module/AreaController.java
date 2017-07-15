package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.RegionService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;


/**
 * 区域相关controller，用于管理区域
 *
 */
@Controller
@RequestMapping("/area")
public class AreaController {
    
    @Autowired
    private AreaService areaService;

    @RequestMapping("/list")
    public String toList(Integer parentId, Model resultModel){

        ResultJson<List<AreaVO>> result = areaService.findByParent(parentId);
        resultModel.addAttribute("dataList",result.getData());

        return "modules/area/areaList";
    }

    /**
     * 跳转区域业务首页-区域列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Integer areaId, Model resultModel){

        areaId = (areaId == null)? 0 : areaId;

        // 所属公司的树状结构
        String parentTreeJson = findAreaTreeStr();
        ResultJson<List<AreaVO>> result = areaService.findByParent(areaId);

        resultModel.addAttribute("dataList",result.getData());
        resultModel.addAttribute("parentTreeJson", parentTreeJson);
        return "modules/area/list";
    }

    /**
     * 新增地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> add(BaseArea baseArea){
        return areaService.addArea(baseArea);
    }

    /**
     * 更新地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> update(BaseArea baseArea, HttpServletRequest request){
        return areaService.updateArea(baseArea);
    }

    /**
     * 删除客户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/remove", method = RequestMethod.POST)
    public ResultJson<Void> remove(Integer id, HttpServletRequest request){
        return areaService.remove(id);
    }

    /**
     * 跳转新增地域页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Integer parentId, Model model) {

        String parentTreeJson = findAreaTreeStr();
        String parentAreaName = findParentName(parentId);

        model.addAttribute("parentId", parentId);
        model.addAttribute("parentAreaName", parentAreaName);
        model.addAttribute("parentTreeJson", parentTreeJson);

        return "modules/area/add";
    }

    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        String parentTreeJson = findAreaTreeStr();
        BaseArea baseArea = areaService.findById(id).getData();
        String parentName = findParentName(baseArea.getParentId());

        model.addAttribute("baseArea", baseArea);
        model.addAttribute("parentAreaName", parentName);
        model.addAttribute("parentTreeJson", parentTreeJson);

        return "modules/area/update";
    }

    /**
     * 所属区域的树结构
     *
     * @return
     */
    private String findAreaTreeStr() {
        // 所属公司的树状结构
        List<TreeNode> parentNodes = new LinkedList<>();
        parentNodes.addAll(areaService.findAreaNodeList(null).getData());
        String parentTreeJson = null;
        if (parentNodes != null && !parentNodes.isEmpty()) {
            parentTreeJson = JSONObject.toJSONString(parentNodes);
        }
        return parentTreeJson;
    }

    /**
     * 返回上级区域的名称
     *
     * @param parentId
     * @return
     */
    private String findParentName(Integer parentId) {
        // 设置上级公司名称
        String parentAreaName = "";
        if (parentId != null && parentId.intValue() != 0) {
            BaseArea parentArea = areaService.findById(parentId).getData();
            parentAreaName = parentArea.getName();
        }
        return parentAreaName;
    }
}
