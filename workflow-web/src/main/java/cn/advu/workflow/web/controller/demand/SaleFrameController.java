package cn.advu.workflow.web.controller.demand;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrderFrame;
import cn.advu.workflow.domain.fcf_vu.BaseMonitor;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import cn.advu.workflow.web.service.base.MonitorRequestService;
import cn.advu.workflow.web.service.base.SaleFrameService;
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
 * 需求框架相关controller，用于管理需求框架
 *
 */
@Controller
@RequestMapping("/saleFrame")
public class SaleFrameController {

    @Autowired
    SaleFrameService saleFrameService;

    @Autowired
    AreaService areaService;

    @Autowired
    MonitorRequestService monitorRequestService;

    /**
     * 跳转需求框架首页-需求框架列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseExecuteOrderFrame>> result = saleFrameService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "demand/saleFrame/list";
    }

    /**
     * 跳转新增需求单页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model resultModel){

        List<BaseArea> areaList = areaService.findAll().getData();
        resultModel.addAttribute("areaList", areaList);
        resultModel.addAttribute("areaListJson", JSONArray.toJSON(areaList));

        List<BaseMonitor> baseMonitorRequestList = monitorRequestService.findAll().getData();
        resultModel.addAttribute("monitorRequestList", baseMonitorRequestList);

        return "demand/saleFrame/add";
    }

    /**
     * 新增需求单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> add(BaseExecuteOrderFrame baseExecuteOrderFrame, HttpServletRequest request){
        return saleFrameService.add(baseExecuteOrderFrame);
    }
//
//    /**
//     * 更新需求单
//     *
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value ="/update", method = RequestMethod.POST)
//    public ResultJson<Integer> updateArea(BaseExecuteOrder baseExecuteOrder, HttpServletRequest request){
//        return executeOrderService.update(baseExecuteOrder);
//    }
//

//
//
//    /**
//     * 跳转修改页
//     *
//     * @param model
//     * @return
//     */
//    @RequestMapping("/toUpdate")
//    public String toUpdate(Integer id, Model model){
//
//        BaseExecuteOrder baseExecuteOrder = executeOrderService.findById(id).getData();
//
//        model.addAttribute("baseExecuteOrder", baseExecuteOrder);
//
//        return "demand/executeOrder/update";
//    }


}
