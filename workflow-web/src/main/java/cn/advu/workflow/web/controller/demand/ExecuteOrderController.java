package cn.advu.workflow.web.controller.demand;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.manager.*;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import cn.advu.workflow.web.service.base.MonitorRequestService;
import cn.advu.workflow.web.service.base.PersonService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * 需求单相关controller，用于管理需求单
 *
 */
@Controller
@RequestMapping("/executeOrder")
public class ExecuteOrderController {

    @Autowired
    ExecuteOrderService executeOrderService;

    @Autowired
    AreaService areaService;

    @Autowired
    MonitorRequestService monitorRequestService;

    @Autowired
    TreeMananger treeMananger;

    @Autowired
    PersonMananger personMananger;

    @Autowired
    CustomMananger customMananger;

    @Autowired
    IndustryManager industryManager;

    @Autowired
    RegionManager regionManager;

    @RequestMapping("/signCompanySelect")
    public String signCompanySelect(Integer signType, Model model){
        List<BaseCustom> signCompanyList = null;
        if (signType != null) {
            signCompanyList = customMananger.findCustomListByCustomType(signType);
        }
        model.addAttribute("signCompanyList", signCompanyList);
        return "demand/executeOrder/signCompanySelect";
    }

    @RequestMapping("/customSelect")
    public String customSelect(Integer signCustomId, Model model){
        List<BaseCustom> customList = null;
        if (signCustomId != null) {
            customList = customMananger.findChildCustom(signCustomId);
        }
        model.addAttribute("customList", customList);
        return "demand/executeOrder/customSelect";
    }

    /**
     * 跳转需求单首页-需求单列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseExecuteOrder>> result = executeOrderService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "demand/executeOrder/list";
    }

    /**
     * 新增需求单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseExecuteOrder baseExecuteOrder, HttpServletRequest request){
        return executeOrderService.add(baseExecuteOrder);
    }

    /**
     * 更新需求单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateArea(BaseExecuteOrder baseExecuteOrder, HttpServletRequest request){
        return executeOrderService.update(baseExecuteOrder);
    }

    /**
     * 跳转新增需求单页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model resultModel){

        Integer areaId = 6;

        // 区域树
        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        resultModel.addAttribute("areaTreeJson", areaTreeJson);
        // 项目负责人ID
        List<BasePerson> leaderList = personMananger.findPersonListByArea(areaId);
        // 监测机构
        List<BaseMonitor> baseMonitorRequestList = monitorRequestService.findAll().getData();
        List<BaseIndustry> industryList = industryManager.findAllEnabledIndustryList();

        List<BaseRegion> regionList = regionManager.findAllActiveRegionList();

        resultModel.addAttribute("monitorRequestList", baseMonitorRequestList);
        resultModel.addAttribute("leaderList", leaderList);
        resultModel.addAttribute("industryList", industryList);
        resultModel.addAttribute("regionList", regionList);

        return "demand/executeOrder/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(id).getData();

        model.addAttribute("baseExecuteOrder", baseExecuteOrder);

        return "demand/executeOrder/update";
    }


}
