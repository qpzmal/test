package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseDept;
import cn.advu.workflow.domain.fcf_vu.BaseIndustry;
import cn.advu.workflow.domain.fcf_vu.BasePersonExtend;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.DeptService;
import cn.advu.workflow.web.service.base.PersonService;
import net.sf.ehcache.search.parser.MCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/index")
    public String toIndex(Integer areaId, Integer deptId, Model resultModel){

        // 公司列表
        List<BaseArea> areaList = areaService.findAll().getData();
        resultModel.addAttribute("areaList", areaList);

        // 部门列表
        List<TreeNode> deptNodes = new LinkedList<>();
        List<BasePersonExtend> personList = null;

        if (areaId != null) {
            // 初始化部门列表
            List<BaseDept> deptList = deptService.findAreaDept(areaId).getData();
            deptNodes.addAll(deptService.findDeptNodeList(deptList).getData());
            personList = personService.findPersonByDept(areaId, deptId).getData();
        }

        resultModel.addAttribute("areaId", areaId);
        resultModel.addAttribute("deptId", deptId);
        resultModel.addAttribute("deptNodes", deptNodes);
        resultModel.addAttribute("dataList", personList);

        return "modules/person/list";
    }

    /**
     * 跳转新增员工页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model resultModel){

        List<BaseArea> areaList = areaService.findAll().getData();
        resultModel.addAttribute("areaList", areaList);

//        List<BaseIndustry> industryList = industryService.findAll().getData();
//        resultModel.addAttribute("industryList", industryList);
        return "modules/person/add";
    }
}
