package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.manager.PersonMananger;
import cn.advu.workflow.web.manager.TreeMananger;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.DeptService;
import cn.advu.workflow.web.service.base.PersonService;
import net.sf.ehcache.search.parser.MCriteria;
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
@RequestMapping("/person")
public class PersonController {


    @Autowired
    AreaService areaService;
    @Autowired
    DeptService deptService;

    @Autowired
    PersonService personService;
    @Autowired
    PersonMananger personMananger;

    @Autowired
    TreeMananger treeMananger;

    @RequestMapping("/deptPersonSelect")
    public String deptList(Integer deptId, Model model){

        List<BasePerson> parentList = null;
        if (deptId != null) {
            parentList = personMananger.findPersonByDept(deptId);
        }
        model.addAttribute("parentList", parentList);

        return "modules/person/deptPersonSelect";
    }

    @RequestMapping("/personList")
    public String deptList(Integer areaId, Integer deptId, Model model){
        // 列表展示
        List<BasePersonExtend> personList = personService.findPersonByDept(areaId, deptId).getData();
        model.addAttribute("dataList", personList);

        return "modules/person/personList";
    }

    @RequestMapping("/index")
    public String toIndex(Integer areaId, Integer deptId, Model resultModel){
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

        // 部门列表
        List<BasePersonExtend> personList = personService.findPersonByDept(areaId, deptId).getData();

        resultModel.addAttribute("areaId", areaId);
        resultModel.addAttribute("deptId", deptId);
        resultModel.addAttribute("dataList", personList);
        resultModel.addAttribute("areaName", areaName);
        resultModel.addAttribute("areaTreeJson", areaTreeJson);
        resultModel.addAttribute("parentTreeJson", parentTreeJson);

        return "modules/person/list";
    }

    /**
     * 跳转新增员工页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Integer areaId, Integer deptId, Model model){

        // 区域树
        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        // 部门树
        String deptTreeJson = null;
        String areaName = null;
        if (areaId != null) {
            deptTreeJson = treeMananger.converToTreeJsonStr(deptService.findAreaDeptNodeList(areaId).getData());
            BaseArea baseArea = areaService.findById(areaId).getData();
            areaName = baseArea.getName();
        }
        String deptName = "";
        List<BasePerson> parentList = null;
        if (deptId != null) {
            BaseDept parentDept = deptService.findById(deptId).getData();
            deptName = parentDept.getName();
            parentList = personMananger.findPersonByDept(deptId);
        }

        model.addAttribute("deptName", deptName);
        model.addAttribute("areaId", areaId);
        model.addAttribute("areaName", areaName);
        model.addAttribute("deptId", deptId);

        model.addAttribute("parentList", parentList);
        model.addAttribute("areaTreeJson", areaTreeJson);
        model.addAttribute("deptTreeJson", deptTreeJson);

        return "modules/person/add";
    }

    /**
     * 新增部门
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> add(BasePerson basePerson, HttpServletRequest request){
        return personService.add(basePerson);
    }
}
