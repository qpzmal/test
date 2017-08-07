package cn.advu.workflow.web.controller.demand;

import cn.advu.workflow.domain.fcf_vu.*;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.common.loginContext.UserThreadLocalContext;
import cn.advu.workflow.web.constants.MessageConstants;
import cn.advu.workflow.web.manager.*;
import cn.advu.workflow.web.service.base.AreaService;
import cn.advu.workflow.web.service.base.BuyFrameService;
import cn.advu.workflow.web.service.base.MonitorRequestService;
import cn.advu.workflow.web.util.AssertUtil;
import cn.advu.workflow.web.util.BigDecimalUtil;
import cn.advu.workflow.web.util.StringListUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 需求框架相关controller，用于管理需求框架
 *
 */
@Controller
@RequestMapping("/buyFrame")
public class BuyFrameController {

    @Autowired
    BuyFrameService buyFrameService;

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
    UserMananger userMananger;

    @Autowired
    IndustryManager industryManager;

    @Autowired
    RegionManager regionManager;

    @Autowired
    MediaMananger mediaMananger;

    @Autowired
    AdtypeMananger adtypeMananger;


    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 跳转需求框架首页-需求框架列表页
     *
     * @param resultModel
     * @return
     */
    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        BaseBuyOrderFrame param = new BaseBuyOrderFrame();
        param.setStatus((byte) -1);
        ResultJson<List<BaseBuyOrderFrame>> result = buyFrameService.findAll(param);
        resultModel.addAttribute("dataList",result.getData());
        return "demand/buyFrame/list";
    }

    /**
     * 跳转新增需求单页面
     *
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAdd(Model resultModel){


        Integer userId = Integer.valueOf(UserThreadLocalContext.getCurrentUser().getUserId());
        SysUser sysUser = userMananger.findById(userId);
        BasePerson basePerson = personMananger.findPersonByName(sysUser.getUserName());
        AssertUtil.assertNotNull(basePerson, MessageConstants.PERSON_IS_NOT_EXISTS);

        Integer areaId = basePerson.getAreaId();

        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        List<BasePerson> leaderList = personMananger.findPersonListByArea(areaId);
        List<BaseRegion> regionList = regionManager.findAllActiveRegionList();
        List<BaseMedia> mediaList = mediaMananger.findAllActiveMedia();
        List<BaseAdtype> adtypeList = adtypeMananger.findAllActive();


        resultModel.addAttribute("areaTreeJson", areaTreeJson);
        resultModel.addAttribute("leaderList", leaderList);
        resultModel.addAttribute("regionList", regionList);
        resultModel.addAttribute("salePersonId", basePerson.getId());
        resultModel.addAttribute("salePersonName", basePerson.getName());
        resultModel.addAttribute("mediaListJson", JSONArray.toJSONString(mediaList));
        resultModel.addAttribute("adtypeListJson", JSONArray.toJSONString(adtypeList));
        resultModel.addAttribute("std", BigDecimalUtil.HUNDRED);


        return "demand/buyFrame/add";
    }

    /**
     * 新增需求单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public ResultJson<Integer> add(BaseBuyOrderFrame baseBuyOrderFrame, HttpServletRequest request){
        return buyFrameService.add(baseBuyOrderFrame);
    }

    /**
     * 更新需求单
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> update(BaseBuyOrderFrame baseBuyOrderFrame, HttpServletRequest request){
        return buyFrameService.update(baseBuyOrderFrame);
    }


    @ResponseBody
    @RequestMapping(value ="/remove", method = RequestMethod.POST)
    public ResultJson<Void> remove(Integer id, HttpServletRequest request){
        return buyFrameService.remove(id);
    }

    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id, Model model){

        Integer userId = Integer.valueOf(UserThreadLocalContext.getCurrentUser().getUserId());
        SysUser sysUser = userMananger.findById(userId);
        BasePerson basePerson = personMananger.findPersonByName(sysUser.getUserName());

        BaseBuyOrderFrame baseBuyOrderFrame = buyFrameService.findById(id).getData();
        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        BaseArea baseArea = areaService.findById(baseBuyOrderFrame.getAreaId()).getData();
        List<BasePerson> leaderList = personMananger.findPersonListByArea(baseBuyOrderFrame.getAreaId());
        List<BaseIndustry> industryList = industryManager.findAllEnabledIndustryList();
        List<BaseRegion> regionList = regionManager.findAllActiveRegionList();
        List<BaseMonitor> baseMonitorRequestList = monitorRequestService.findAll().getData();
        List<BaseMedia> mediaList = mediaMananger.findAllActiveMedia();
        List<BaseAdtype> adtypeList = adtypeMananger.findAllActive();

        List<BaseOrderCpmVO> cpmList = baseBuyOrderFrame.getBaseOrderCpmList();
        int index = 1;
        JSONArray cpmArrList = new JSONArray();
        for (BaseOrderCpm cpmTemp : cpmList) {
            JSONObject cpmVo = new JSONObject();
            cpmVo.put("state", false);
            cpmVo.put("num", index++);
            cpmVo.put("id", cpmTemp.getId());
            cpmVo.put("mediaId", cpmTemp.getMediaId());
            cpmVo.put("mediaPrice", cpmTemp.getMediaPrice());
            cpmVo.put("firstPrice", cpmTemp.getFirstPrice());
            cpmVo.put("adTypeId", cpmTemp.getAdTypeId());
            cpmVo.put("cpm", cpmTemp.getCpm());
            cpmVo.put("remark", cpmTemp.getRemark());
            cpmArrList.add(cpmVo);
        }
        baseBuyOrderFrame.setCpmJsonStr(cpmArrList.toJSONString());

        String areaName = (baseArea==null)?"":baseArea.getName();

        model.addAttribute("selectedReginList", StringListUtil.toList(baseBuyOrderFrame.getDeliveryAreaIds()));
        model.addAttribute("monitorRequestList", baseMonitorRequestList);
        model.addAttribute("regionList", regionList);
        model.addAttribute("industryList", industryList);
        model.addAttribute("areaName", areaName);
        model.addAttribute("leaderList", leaderList);
        model.addAttribute("areaTreeJson", areaTreeJson);
        model.addAttribute("mediaListJson", JSONArray.toJSONString(mediaList));
        model.addAttribute("adtypeListJson", JSONArray.toJSONString(adtypeList));
        model.addAttribute("format", format);

        model.addAttribute("baseBuyOrder", baseBuyOrderFrame);
        model.addAttribute("std", BigDecimalUtil.HUNDRED);


        return "demand/buyFrame/update";
    }

    /**
     * 跳转修改页
     *
     * @param model
     * @return
     */
    @RequestMapping("/refer")
    public String refer(Integer id, Model model){

        Integer userId = Integer.valueOf(UserThreadLocalContext.getCurrentUser().getUserId());
        SysUser sysUser = userMananger.findById(userId);
        BasePerson basePerson = personMananger.findPersonByName(sysUser.getUserName());

        BaseBuyOrderFrame baseBuyOrderFrame = buyFrameService.findById(id).getData();
        String areaTreeJson = treeMananger.converToTreeJsonStr(areaService.findAreaNodeList(null).getData());
        BaseArea baseArea = areaService.findById(baseBuyOrderFrame.getAreaId()).getData();
        List<BasePerson> leaderList = personMananger.findPersonListByArea(baseBuyOrderFrame.getAreaId());
        List<BaseIndustry> industryList = industryManager.findAllEnabledIndustryList();
        List<BaseRegion> regionList = regionManager.findAllActiveRegionList();
        List<BaseMonitor> baseMonitorRequestList = monitorRequestService.findAll().getData();
        List<BaseMedia> mediaList = mediaMananger.findAllActiveMedia();
        List<BaseAdtype> adtypeList = adtypeMananger.findAllActive();

        List<BaseOrderCpmVO> cpmList = baseBuyOrderFrame.getBaseOrderCpmList();
        int index = 1;
        JSONArray cpmArrList = new JSONArray();
        for (BaseOrderCpmVO cpmTemp : cpmList) {
            JSONObject cpmVo = new JSONObject();
            cpmVo.put("num", index++);
            cpmVo.put("id", cpmTemp.getId());
            cpmVo.put("mediaName", cpmTemp.getMediaName());
            cpmVo.put("mediaPrice", cpmTemp.getMediaPrice());
            cpmVo.put("firstPrice", cpmTemp.getFirstPrice());
            cpmVo.put("adTypeName", cpmTemp.getAdTypeName());
            cpmVo.put("cpm", cpmTemp.getCpm());
            cpmVo.put("remark", cpmTemp.getRemark());
            cpmArrList.add(cpmVo);
        }
        baseBuyOrderFrame.setCpmJsonStr(cpmArrList.toJSONString());

        String areaName = (baseArea==null)?"":baseArea.getName();

        model.addAttribute("selectedReginList", StringListUtil.toList(baseBuyOrderFrame.getDeliveryAreaIds()));
        model.addAttribute("monitorRequestList", baseMonitorRequestList);
        model.addAttribute("regionList", regionList);
        model.addAttribute("industryList", industryList);
        model.addAttribute("areaName", areaName);
        model.addAttribute("leaderList", leaderList);
        model.addAttribute("areaTreeJson", areaTreeJson);
        model.addAttribute("mediaListJson", JSONArray.toJSONString(mediaList));
        model.addAttribute("adtypeListJson", JSONArray.toJSONString(adtypeList));
        model.addAttribute("format", format);

        model.addAttribute("baseBuyOrder", baseBuyOrderFrame);
        model.addAttribute("std", BigDecimalUtil.HUNDRED);

        return "demand/buyFrame/refer";
    }

}
