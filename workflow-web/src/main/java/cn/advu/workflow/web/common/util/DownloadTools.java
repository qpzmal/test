package cn.advu.workflow.web.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

public class DownloadTools {
    public static void downExportFile(String filePath, HttpServletRequest request, HttpServletResponse response) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            response.sendError(404, "File Not found");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(filePath));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); // 非常重要
        String fileName = file.getName();
        byte[] b = fileName.getBytes("GBK");
        fileName = new String(b, "iso8859-1");
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
}
