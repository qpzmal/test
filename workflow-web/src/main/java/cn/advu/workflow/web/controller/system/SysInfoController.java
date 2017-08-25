package cn.advu.workflow.web.controller.system;

import cn.advu.workflow.domain.fcf_vu.SysInfo;
import cn.advu.workflow.web.common.ResultJson;
import cn.advu.workflow.web.service.system.SysInfoService;
import cn.advu.workflow.web.util.DateUtil;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
@RequestMapping("sysInfo")
public class SysInfoController {

    private static Logger LOGGER = LoggerFactory.getLogger(SysInfoController.class);

    @Autowired
    private SysInfoService sysInfoService;

    @Value("${upload.img.base}")
    private String uploadImgBase;
    @Value("${upload.img.logo}")
    private String uploadImgSysLOGO;

    @RequestMapping("/index")
    public String toIndex(Model resultModel){
        ResultJson<SysInfo> result = sysInfoService.querySysInfo();
        resultModel.addAttribute("sysInfo", result.getData());
//        DisplayTool.buttonDisplay(resultModel, "modify", "");
        return "system/sys/index";
    }


    /**
     * 更新
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="/update", method = RequestMethod.POST)
    public ResultJson<Integer> updateSysInfo(SysInfo sysInfo, HttpServletRequest request){
        return sysInfoService.updateSelective(sysInfo);
    }


    /**
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="fileUpload",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String fileUpload(Model model
//                             @RequestParam("file") CommonsMultipartFile[] imgFile,
            ,HttpServletRequest request
    ){
//        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> imgFileList = multipartRequest.getFiles("file");
        LOGGER.info("共上传【{}】个文件", imgFileList.size());

        String path = uploadImgBase;
        path = path + (new DateTime().getYear()) + uploadImgSysLOGO;
        LOGGER.info("文件上传路径：{}", path);
        if(!new File(path).exists())   {
            new File(path).mkdirs();
        }

        DateTime dateTime=new DateTime();
        for (MultipartFile file:imgFileList) {
            String fileName = file.getOriginalFilename();
            if (fileName.length() > 30) { // 文件名过长时，改名
                fileName = System.currentTimeMillis() + ".jpg";
            }
            fileName = "LOGO_" + dateTime.toString(DateUtil.DATE_FORMAT_NO_SYMBOL_24H) + "_" + fileName;
            String filePath = path + fileName;
            LOGGER.debug("file path:{}", filePath);
            LOGGER.debug("file path:{}", filePath.substring(filePath.indexOf("/workflow-admin"), filePath.length()));

            try {
                FileOutputStream fout = new FileOutputStream(filePath);
                IOUtils.write(file.getBytes(), fout);
                fout.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            SysInfo sysInfo = new SysInfo();
            sysInfo.setId(1);
            sysInfo.setLogo(filePath.substring(filePath.indexOf("/workflow-admin"), filePath.length()));
            sysInfoService.updateSelective(sysInfo);
        }

        return "";
    }


}
