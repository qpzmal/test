package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseAdtype;
import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.constant.WebConstants;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.dto.module.DeptVO;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.DeptService;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
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

    @RequestMapping("/deptTreeList")
    public ResultJson<String> deptTreeList(Integer areaId) {
        ResultJson resultJson = new ResultJson(WebConstants.OPERATION_SUCCESS);
        List<TreeNode> deptNodes = new LinkedList<>();
        deptNodes.addAll(deptService.findAreaDeptNodeList(areaId).getData());
        String deptTreeJson = JSONObject.toJSONString(deptNodes);
        resultJson.setData(deptTreeJson);
        return resultJson;
    }


    @RequestMapping("/deptList")
//    @ResponseBody
    public String toIndex(Integer areaId, Integer parentId, Model model){

        // 列表展示
        List<DeptVO> deptVOList = null;

        if (parentId != null) {

            List<BaseDept> deptList = deptService.findChildDept(areaId, parentId).getData();

            BaseArea baseArea = areaService.findById(areaId).getData();

            deptVOList = buildDeptVOList(deptList, baseArea);

        }

        model.addAttribute("dataList", deptVOList);

        return "modules/dept/deptList";
    }

    @RequestMapping("/index")
    public String toIndex(Integer areaId, Model resultModel){

        // 列表展示
        List<DeptVO> deptVOList = null;
        List<TreeNode> deptNodes = new LinkedList<>();

        if (areaId != null) {

            List<BaseDept> deptList = deptService.findAreaDept(areaId).getData();

            BaseArea baseArea = areaService.findById(areaId).getData();

            deptVOList = buildDeptVOList(deptList, baseArea);

            deptNodes.addAll(deptService.findDeptNodeList(deptList).getData());
        }

        // 公司列表
        List<BaseArea> areaList = areaService.findAll().getData();

        resultModel.addAttribute("dataList", deptVOList);
        resultModel.addAttribute("deptNodes", deptNodes);
        resultModel.addAttribute("areaList", areaList);
        resultModel.addAttribute("areaId", areaId);
        return "modules/dept/list";
    }

    /**
     * 跳转新增部门页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Integer areaId, Integer parentId, Model model) {

        List<BaseArea> areaList = areaService.findAll().getData();

        List<TreeNode> deptNodes = new LinkedList<>();
        if (areaId != null) {
            deptNodes.addAll(deptService.findAreaDeptNodeList(areaId).getData());
        }

        String parentDeptName = "";
        if (parentId != null) {
            BaseDept parentDept = deptService.findById(parentId).getData();
            parentDeptName = parentDept.getName();
        }

        String deptTreeJson = JSONObject.toJSONString(deptNodes);

        model.addAttribute("parentDeptName", parentDeptName);
        model.addAttribute("parentId", parentId);
        model.addAttribute("deptTreeJson", deptTreeJson);

        // 设置默认的所属公司及上级部门
        model.addAttribute("assignAreaId", areaId);
        model.addAttribute("assignParentId", parentId);
        // 设置公司列表
        model.addAttribute("areaList", areaList);

        return "modules/dept/add";
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


    private List<DeptVO> buildDeptVOList(List<BaseDept> deptList, BaseArea baseArea) {

        List<DeptVO> deptVOList = new LinkedList<>();

        for (BaseDept baseDept : deptList) {

            DeptVO deptVO = new DeptVO();
            deptVO.setBaseDept(baseDept);

            // 设置公司名称、上级部门名称
            Integer parentId = baseDept.getParentId();
            if (parentId != null && parentId != -1) {
                BaseDept parentDept = deptService.findById(parentId).getData();
                deptVO.setParentName(parentDept.getName());
            }
            deptVO.setAreaName(baseArea.getName());

            deptVOList.add(deptVO);
        }
        return deptVOList;
    }
}
