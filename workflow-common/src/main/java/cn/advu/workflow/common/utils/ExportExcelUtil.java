package cn.advu.workflow.common.utils;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */
public class ExportExcelUtil {

    /**
     * excel导出输出到浏览器
     * @param response
     * @param name 文件名
     * @param title 表头名
     * @param data 数据
     */
    public static void export(HttpServletResponse response, String name, List<String> title, List<List<Object>> data){
        OutputStream out =null;
        try {
            XSSFWorkbook workBook=new XSSFWorkbook();
            XSSFSheet sheet=workBook.createSheet(name);
            XSSFRow row = sheet.createRow((short)0);
            XSSFCell cell=null;

            //写入表头
            for(int i = 0;i < title.size();i++){
                //第一行赋值
                cell=row.createCell((short)i);
                cell.setCellValue(title.get(i).toString());
            }

            //写入数据
            for(int i = 0;i < data.size();i++){
                row=sheet.createRow(i+1);
                List<Object> item = data.get(i);
                for(int j = 0;j < item.size();j++){
                    cell=row.createCell(j);
                    cell.setCellValue(item.get(j).toString());
                }
            }

            //使用response输出到浏览器
            out = response.getOutputStream();//输出流
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("name", name+".xlsx");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setDateHeader("Expires", 0);
            response.setHeader("Content-disposition","attachment; filename=\""+ URLEncoder.encode( name+".xlsx", "UTF-8")+ "\"");
            workBook.write(out); // workBook
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
