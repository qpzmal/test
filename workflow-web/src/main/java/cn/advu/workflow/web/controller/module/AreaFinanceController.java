package cn.advu.workflow.web.controller.module;

import cn.advu.workflow.domain.fcf_vu.BaseArea;
import cn.advu.workflow.domain.fcf_vu.BaseAreaFinance;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.dto.TreeNode;
import cn.advu.workflow.web.manager.TreeMananger;
import cn.advu.workflow.web.service.base.AreaFinanceService;
import cn.advu.workflow.web.service.base.AreaService;
import com.alibaba.fastjson.JSONArray;
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
 * 区域财务结算相关controller，用于管理区域财务结算
 *
 */
@Controller
@RequestMapping("/areaFinance")
public class AreaFinanceController {
    
    @Autowired
    private AreaService areaService;

    @Autowired
    private AreaFinanceService areaFinanceService;

    @Autowired
    private TreeMananger treeMananger;

    /**
     * 跳转区域业务首页-区域列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(String areaIdJsonStr, Model resultModel){

        // 所属公司的树状结构
        List<TreeNode> parentNodes = new LinkedList<>();
        parentNodes.addAll(areaService.findAreaNodeList(null).getData());
        String parentTreeJson = null;
        if (parentNodes != null && !parentNodes.isEmpty()) {
            parentTreeJson = JSONObject.toJSONString(parentNodes);
        }

        JSONArray areaIdArr = JSONArray.parseArray(areaIdJsonStr);
        List<Integer> areaIdList = new LinkedList<>();



//        List<BaseAreaFinance> dataList = areaFinanceService.findByArea(areaId).getData();
//        resultModel.addAttribute("dataList", dataList);
        resultModel.addAttribute("parentTreeJson", parentTreeJson);
        return "modules/areaFinance/list";
    }

    /**
     * 新增地域结算
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> add(BaseAreaFinance baseAreaFinance, HttpServletRequest request){
        return areaFinanceService.add(baseAreaFinance);
    }

    /**
     * 更新地域
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateArea(BaseArea baseArea, HttpServletRequest request){
        return areaService.updateArea(baseArea);
    }

    /**
     * 跳转新增地域页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Integer areaId, Model model){

        // 所属公司的树状结构
        String parentTreeJson = treeMananger.converToTreeJsonStr(
                areaService.findAreaNodeList(null).getData()
        );

        // 设置所属公司名称
        String areaName = "";
        if (areaId != null) {
            BaseArea parentArea = areaService.findById(areaId).getData();
            areaName = parentArea.getName();
        }

        model.addAttribute("areaId", areaId);
        model.addAttribute("areaName", areaName);
        model.addAttribute("parentTreeJson", parentTreeJson);

        return "modules/areaFinance/add";
    }


    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        BaseArea baseArea = areaService.findById(id).getData();

        model.addAttribute("baseArea", baseArea);

        return "modules/area/update";
    }


}
