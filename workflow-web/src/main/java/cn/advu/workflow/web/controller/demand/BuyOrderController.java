package cn.advu.workflow.web.controller.demand;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseBuyOrder;
import cn.advu.workflow.domain.fcf_vu.BaseExecuteOrder;
import cn.advu.workflow.domain.fcf_vu.BaseMonitorRequest;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.BuyOrderService;
import cn.advu.workflow.web.service.base.ExecuteOrderService;
import cn.advu.workflow.web.service.base.MonitorRequestService;
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
@RequestMapping("/buyOrder")
public class BuyOrderController {

    @Autowired
    BuyOrderService buyOrderService;

    @Autowired
    AreaService areaService;

    @Autowired
    MonitorRequestService monitorRequestService;

    /**
     * 跳转需求单首页-需求单列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<List<BaseBuyOrder>> result = buyOrderService.findAll();
        resultModel.addAttribute("dataList",result.getData());
        return "demand/buyOrder/list";
    }

    /**
     * 新增需求单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> addRegion(BaseBuyOrder baseBuyOrder, HttpServletRequest request){
        return buyOrderService.add(baseBuyOrder);
    }

    /**
     * 更新需求单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateArea(BaseBuyOrder baseBuyOrder, HttpServletRequest request){
        return buyOrderService.update(baseBuyOrder);
    }

    /**
     * 跳转新增需求单页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model resultModel){
        List<BaseBuyOrder> buyOrderList = buyOrderService.findAll().getData();
        resultModel.addAttribute("buyOrderList", buyOrderList);

        List<BaseArea> areaList = areaService.findAll().getData();
        resultModel.addAttribute("areaList", areaList);

        List<BaseMonitorRequest> baseMonitorRequestList = monitorRequestService.findAll().getData();
        resultModel.addAttribute("monitorRequestList", baseMonitorRequestList);

        return "demand/buyOrder/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseBuyOrder baseBuyOrder = buyOrderService.findById(id).getData();

        model.addAttribute("baseBuyOrder", baseBuyOrder);

        return "demand/buyOrder/update";
    }


}
