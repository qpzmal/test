package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.tool.DisplayTool;
import cn.advu.workflow.web.manager.PersonMananger;
import cn.advu.workflow.web.manager.TreeMananger;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.DeptService;
import cn.advu.workflow.web.service.base.PersonService;
import cn.advu.workflow.web.service.system.SysUserService;
import cn.advu.workflow.web.util.AssertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 通讯录相关controller，用于管理部门
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
    SysUserService sysUserService;

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
        DisplayTool.buttonDisplay(resultModel, "add", "10501");
        DisplayTool.buttonDisplay(resultModel, "modify", "10502");
        DisplayTool.buttonDisplay(resultModel, "delete", "10503");

        return "modules/person/list";
    }

    /**
     * 跳转新增员工页面
     *
     * 20170827 weiqz 为保持sys_user和base_person同步，关闭person的添加入口
     *
     * @return
     */
//    @RequestMapping("/toAdd")
//    public String toAdd(Integer areaId, Integer deptId, Model model){
//
//        // 区域树
//        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
//        // 部门树
//        String deptTreeJson = null;
//        String areaName = null;
//        if (areaId != null) {
//            deptTreeJson = treeMananger.converToTreeJsonStr(deptService.findAreaDeptNodeList(areaId).getData());
//            BaseArea baseArea = areaService.findById(areaId).getData();
//            areaName = baseArea.getName();
//        }
//        String deptName = "";
//        List<BasePerson> parentList = null;
//        if (deptId != null) {
//            BaseDept parentDept = deptService.findById(deptId).getData();
//            deptName = parentDept.getName();
//            parentList = personMananger.findPersonByDept(deptId);
//        }
//
//        model.addAttribute("deptName", deptName);
//        model.addAttribute("areaId", areaId);
//        model.addAttribute("areaName", areaName);
//        model.addAttribute("deptId", deptId);
//
//        model.addAttribute("parentList", parentList);
//        model.addAttribute("areaTreeJson", areaTreeJson);
//        model.addAttribute("deptTreeJson", deptTreeJson);
//        DisplayTool.buttonDisplay(model, "add", "10501");
//
//        return "modules/person/add";
//    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model) {

        // 区域树
        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        // 部门树
        String deptTreeJson = null;

        BasePerson basePerson = personService.findById(id).getData();
        AssertUtil.assertNotNull(basePerson);

        Integer areaId = basePerson.getAreaId();
        Integer deptId = basePerson.getDeptId();

        String areaName = "";
        if (areaId != null && areaId != 0) {
            deptTreeJson = treeMananger.converToTreeJsonStr(deptService.findAreaDeptNodeList(areaId).getData());
            BaseArea baseArea = areaService.findById(areaId).getData();
            areaName = baseArea.getName();
        }

        List<BasePerson> parentList = null;
        String deptName = "";
        if (deptId != null && deptId != 0) {
            BaseDept parentDept = deptService.findById(deptId).getData();
            deptName = parentDept.getName();
            parentList = personMananger.findPersonByDept(deptId);
        }
        model.addAttribute("deptName", deptName);
        model.addAttribute("areaName", areaName);

        model.addAttribute("parentList", parentList);
        model.addAttribute("areaTreeJson", areaTreeJson);
        model.addAttribute("deptTreeJson", deptTreeJson);
        model.addAttribute("basePerson", basePerson);
        DisplayTool.buttonDisplay(model, "modify", "10502");


        return "modules/person/update";

    }

    @RequestMapping("/toRefer")
    public String toRefer(Integer id, Model model) {



        BasePerson basePerson = personService.findById(id).getData();
        AssertUtil.assertNotNull(basePerson);

        Integer areaId = basePerson.getAreaId();
        Integer deptId = basePerson.getDeptId();

        String areaName = "";
        if (areaId != null) {
            BaseArea baseArea = areaService.findById(areaId).getData();
            if (baseArea != null) {
                areaName = baseArea.getName();
            }
        }

        List<BasePerson> parentList = null;
        String deptName = "";
        BaseDept parentDept = deptService.findById(deptId).getData();
        if (parentDept != null) {
            deptName = parentDept.getName();
            parentList = personMananger.findPersonByDept(deptId);
        }
        model.addAttribute("deptName", deptName);
        model.addAttribute("areaName", areaName);

        model.addAttribute("parentList", parentList);
        model.addAttribute("basePerson", basePerson);

        return "modules/person/refer";

    }

    /**
     * 新增用户
     *
     * 20170827 weiqz 为保持sys_user和base_person同步，关闭person的添加入口
     *
     * @return
     */
//    @ResponseBody
//    @RequestMapping(value ="/add", method = RequestMethod.POST)
//    public ResultJson<Integer> add(BasePerson basePerson, HttpServletRequest request){
//        return personService.add(basePerson);
//    }

    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Void> update(BasePerson basePerson, HttpServletRequest request){
        SysUser sysUser = new SysUser();
        sysUser.setId(Integer.valueOf(basePerson.getUid()));
        sysUser.setAddress(basePerson.getAddress());
        sysUser.setEmail(basePerson.getEmail());
        sysUser.setMobile(basePerson.getMobile());
        sysUserService.update(sysUser);
        return personService.update(basePerson);
    }


    @ResponseBody
    @RequestMapping(value ="/remove", method = RequestMethod.POST)
    public ResultJson<Void> remove(Integer id, HttpServletRequest request){
        return personService.remove(id);
    }
}
