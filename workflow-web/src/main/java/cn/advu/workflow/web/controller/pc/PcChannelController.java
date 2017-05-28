package cn.advu.workflow.web.controller.pc;

import cn.advu.workflow.common.golbal.Page;
import cn.advu.workflow.web.service.pc.PcBaseDataService;
import cn.advu.workflow.web.service.pc.PcBookReportService;
import cn.advu.workflow.web.service.pc.PcConsumerDataService;
import cn.advu.workflow.web.service.pc.PcRechargeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by BBD on 2017/5/10.
 */
@Controller
@RequestMapping("/pc/channel")
public class PcChannelController {
    private static final Logger LOGGER = Logger.getLogger(PcChannelController.class);
    @Autowired
    private PcBaseDataService pcBaseDataService;
    @Autowired
    private PcConsumerDataService pcConsumerDataService;
    @Autowired
    private PcBookReportService pcBookReportService;
    @Autowired
    private PcRechargeService pcRechargeService;

    @RequestMapping(value = "/index")
    public String index(Model model, HttpServletRequest request){
        LOGGER.info("PC数据总览数据查询");
        try {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if (startTime == null || "".equals(startTime)) {
                startTime = getTime();
            }
            if (endTime == null || "".equals(endTime)) {
                endTime = getTime();
            }
            String cnid = request.getParameter("cnid");
            String[] cnids = null;
            if (cnid != null && !"".equals(cnid)) {
                cnids = cnid.split(",");
            }
            LOGGER.info("开始时间-" + startTime + "结束时间-" + endTime + "渠道-" + cnid);
            Page page = new Page();
            page.setPageIndex(Integer.parseInt(request.getParameter("pageIndex") == null ? "1" : request.getParameter("pageIndex")));
            page.setPageSize(Integer.parseInt(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize")));
            page = pcBaseDataService.getPcBaserDatas(page, startTime, endTime, cnids);
            model.addAttribute("page", page);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            model.addAttribute("cnid", cnid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pc/channel/index";
    }

    @RequestMapping(value = "/totaldata")
    public String totaldata(Model model, HttpServletRequest request){
        LOGGER.info("PC渠道总数据查询");
        try {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if (startTime == null || "".equals(startTime)) {
                startTime = getTime();
                LOGGER.info("startTime:"+startTime);
            }
            if (endTime == null || "".equals(endTime)) {
                endTime = getTime();
                LOGGER.info("endTime:"+endTime);
            }
            String cnid = request.getParameter("cnid");
            String[] cnids = null;
            if (cnid != null && !"".equals(cnid)) {
                cnids = cnid.split(",");
            }
            LOGGER.info("开始时间-" + startTime + "结束时间-" + endTime +"渠道："+cnids);
            Page page = new Page();
            page.setPageIndex(Integer.parseInt(request.getParameter("pageIndex") == null ? "1" : request.getParameter("pageIndex")));
            page.setPageSize(Integer.parseInt(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize")));
            page = pcBaseDataService.getPcTotalDatas(page, startTime, endTime,cnids);
            model.addAttribute("page", page);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            model.addAttribute("cnid",cnid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pc/channel/totaldata";
    }


    @RequestMapping(value = "/channelmonthdata")
    public String channelmonthdata(Model model, HttpServletRequest request){
        LOGGER.info("PC渠道月数据查询->");
        try {
            String startTime = request.getParameter("startTime");
            if (startTime == null || "".equals(startTime)) {
                startTime = getMonth();
            }
            String cnid = request.getParameter("cnid");
            String[] cnids = null;
            if (cnid != null && !"".equals(cnid)) {
                cnids = cnid.split(",");
            }
            Page page = new Page();
            page.setPageIndex(Integer.parseInt(request.getParameter("pageIndex") == null ? "1" : request.getParameter("pageIndex")));
            page.setPageSize(Integer.parseInt(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize")));
            page = pcBaseDataService.getPcChannelMonthDatas(page, startTime, cnids);
            model.addAttribute("page", page);
            model.addAttribute("startTime", startTime);
            model.addAttribute("cnid", cnid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pc/channel/channelmonthdata";
    }

    @RequestMapping(value = "/pcbookdata")
    public String chargedata(Model model, HttpServletRequest request){
        LOGGER.info("PC渠道图书据查询->");
        try {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if (startTime == null || "".equals(startTime)) {
                startTime = getTime();
            }
            if (endTime == null || "".equals(endTime)) {
                endTime = getTime();
            }
            String bookId = request.getParameter("bookId");
            String bookName = request.getParameter("bookName");
            Page page = new Page();
            page.setPageIndex(Integer.parseInt(request.getParameter("pageIndex") == null ? "1" : request.getParameter("pageIndex")));
            page.setPageSize(Integer.parseInt(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize")));
            page = pcBookReportService.getPcBookDatas(page, startTime, endTime, bookId, bookName);
            model.addAttribute("page", page);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            model.addAttribute("bookId", bookId);
            model.addAttribute("bookName", bookName);
        }catch (Exception e){
         e.printStackTrace();
        }
        return "pc/channel/channelpcbookdata";
    }

    @RequestMapping(value = "/consumerdata")
    public String consumerdata(Model model, HttpServletRequest request){
        LOGGER.info("PC渠道消费数据查询->");
        try {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if (startTime == null || "".equals(startTime)) {
                startTime = getTime();
            }
            if (endTime == null || "".equals(endTime)) {
                endTime = getTime();
            }
            String cnid = request.getParameter("cnid");
            String[] cnids = null;
            if (cnid != null && !"".equals(cnid)) {
                cnids = cnid.split(",");
            }
            Page page = new Page();
            page.setPageIndex(Integer.parseInt(request.getParameter("pageIndex") == null ? "1" : request.getParameter("pageIndex")));
            page.setPageSize(Integer.parseInt(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize")));
            page = pcConsumerDataService.getPcConsumerDatas(page, startTime, endTime, cnids);
            model.addAttribute("page", page);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            model.addAttribute("cnid", cnid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pc/channel/channelconsumerdata";
    }
    @RequestMapping(value = "/pcrechargedata")
    public String bookdata(Model model, HttpServletRequest request){
        LOGGER.info("PC渠道充值数据查询->");
        try {
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
            if (startTime == null || "".equals(startTime)) {
                startTime = getTime();
            }
            if (endTime == null || "".equals(endTime)) {
                endTime = getTime();
            }
            String cnid = request.getParameter("cnid");
            String[] cnids = null;
            if (cnid != null && !"".equals(cnid)) {
                cnids = cnid.split(",");
            }
            Integer type = request.getParameter("type") == null ? 0 : Integer.parseInt(request.getParameter("type"));
            Page page = new Page();
            page.setPageIndex(Integer.parseInt(request.getParameter("pageIndex") == null ? "1" : request.getParameter("pageIndex")));
            page.setPageSize(Integer.parseInt(request.getParameter("pageSize") == null ? "10" : request.getParameter("pageSize")));
            page = pcRechargeService.getPcRechargeDatas(page, startTime, endTime, type, cnids);
            model.addAttribute("page", page);
            model.addAttribute("startTime", startTime);
            model.addAttribute("endTime", endTime);
            model.addAttribute("type", type);
            model.addAttribute("cnid",cnid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "pc/channel/pcrechargedata";
    }

    public String getTime(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,-1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    public String getMonth(){
        Date date=new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE,0);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date=calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String dateString = formatter.format(date);
        return dateString;
    }
}
