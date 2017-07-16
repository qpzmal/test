package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.domain.fcf_vu.DeptVO;
import cn.advu.workflow.web.manager.TreeMananger;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.DeptService;
import cn.advu.workflow.web.util.AssertUtil;
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
 * 部门相关controller，用于管理部门
 *
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    AreaService areaService;

    @Autowired
    DeptService deptService;

    @Autowired
    TreeMananger treeMananger;

    @RequestMapping("/deptTreeList")
    public ResultJson<String> deptTreeList(Integer areaId) {

        // 部门树
        String parentTreeJson = null;
        if (areaId != null) {
            parentTreeJson = treeMananger.converToTreeJsonStr(deptService.findAreaDeptNodeList(areaId).getData());
        }

        ResultJson<String>  resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        resultJson.setData(parentTreeJson);
        return resultJson;
    }


    @RequestMapping("/deptList")
    public String deptList(Integer areaId, Integer parentId, Model model){
        // 列表展示
        List<DeptVO> deptVOList = deptService.findChildDeptWithName(areaId, parentId).getData();
        model.addAttribute("dataList", deptVOList);

        return "modules/dept/deptList";
    }

    @RequestMapping("/index")
    public String toIndex(Integer areaId, Integer parentId, Model resultModel){

        // 区域树
        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        // 部门树
        String parentTreeJson = null;
        String areaName = null;
        if (areaId != null) {
            parentTreeJson = treeMananger.converToTreeJsonStr(deptService.findAreaDeptNodeList(areaId).getData());
            BaseArea baseArea = areaService.findById(areaId).getData();
            areaName = baseArea.getName();
        }

        // 列表展示
        List<DeptVO> deptVOList = deptService.findChildDeptWithName(areaId, parentId).getData();

        resultModel.addAttribute("dataList", deptVOList);
        resultModel.addAttribute("areaId", areaId);
        resultModel.addAttribute("areaName", areaName);
        resultModel.addAttribute("parentId", parentId);
        resultModel.addAttribute("areaTreeJson", areaTreeJson);
        resultModel.addAttribute("parentTreeJson", parentTreeJson);
        return "modules/dept/list";
    }

    /**
     * 跳转新增部门页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Integer areaId, Integer parentId, Model model) {

        // 区域树
        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        // 部门树
        String parentTreeJson = null;
        String areaName = null;
        if (areaId != null) {
            parentTreeJson = treeMananger.converToTreeJsonStr(deptService.findAreaDeptNodeList(areaId).getData());
            BaseArea baseArea = areaService.findById(areaId).getData();
            areaName = baseArea.getName();
        }
        String parentDeptName = "";
        if (parentId != null) {
            BaseDept parentDept = deptService.findById(parentId).getData();
            parentDeptName = parentDept.getName();
        }

        model.addAttribute("parentName", parentDeptName);
        model.addAttribute("areaId", areaId);
        model.addAttribute("areaName", areaName);
        model.addAttribute("parentId", parentId);
        model.addAttribute("areaTreeJson", areaTreeJson);
        model.addAttribute("parentTreeJson", parentTreeJson);

        return "modules/dept/add";
    }

    /**
     * 跳转更新部门页面
     *
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {

        // 区域树
        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        BaseDept baseDept = deptService.findById(id).getData();
        AssertUtil.assertNotNull(baseDept, MessageConstants.DEPT_IS_NOT_EXISTS);

        Integer areaId = baseDept.getAreaId();
        AssertUtil.assertNotNull(areaId);

        // 部门树
        String parentTreeJson = treeMananger.converToTreeJsonStr(deptService.findAreaDeptNodeList(areaId).getData());

        // 区域名称
        BaseArea baseArea = areaService.findById(areaId).getData();
        String areaName = baseArea.getName();

        Integer parentId = baseDept.getParentId();

        String parentDeptName = "";
        if (parentId != null && parentId.intValue() != 0) {
            BaseDept parentDept = deptService.findById(parentId).getData();
            parentDeptName = parentDept.getName();
        }

        model.addAttribute("parentName", parentDeptName);
        model.addAttribute("areaName", areaName);
        model.addAttribute("areaTreeJson", areaTreeJson);
        model.addAttribute("parentTreeJson", parentTreeJson);
        model.addAttribute("baseDept", baseDept);

        return "modules/dept/update";
    }

    /**
     * 新增部门
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> add(BaseDept baseDept, HttpServletRequest request){

        return deptService.add(baseDept);
    }


    /**
     * 新增部门
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Void> update(BaseDept baseDept, HttpServletRequest request){
        return deptService.update(baseDept);
    }

}
