package cn.advu.workflow.web.common.util;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 表数据导出为excel, 使用jxl
 * @author Niu Qianghong
 *
 */
public class ExportTools {

    /**
     * 导出到excel, 标题部分无合并单元格
     * @param filePath
     * @param sheetName
     * @param title
     * @param data
     * @throws Exception
     */
    public static void exportPlainExcel(String filePath, String sheetName, List<String> titles, List<List<Object>> data)
            throws Exception {
        //t.xls为要新建的文件名
        WritableWorkbook excel= Workbook.createWorkbook(new File(filePath));
        //生成名为sheetName的工作表，参数0表示这是第一页 
        WritableSheet sheet=excel.createSheet(sheetName,0);
        //添加标题
        for(int i=0; i<titles.size(); i++){
            sheet.addCell(new Label(i, 0, titles.get(i)));
        }
        //写入数据
        for(int i=0; i<data.size(); i++){
            List<Object> item = data.get(i);
            for(int j=0; j<item.size(); j++){
                if(item.get(j) != null){
                    sheet.addCell(new Label(j, i+1, item.get(j).toString()));
                }else{
                    sheet.addCell(new Label(j, i+1, ""));
                }

            }
        }
        excel.write();
        excel.close();
    }
    
    /**
     * 导出到excel, 标题部分有合并单元格
     * @param title
     * @param data
     * @throws Exception
     */
    public static void exportCompoundExcel(String filePath, String sheetName, Map<String, List<String>> titles, List<List<Object>> data) throws Exception {
        //t.xls为要新建的文件名
        WritableWorkbook excel= Workbook.createWorkbook(new File(filePath));
        //生成名为sheetName的工作表，参数0表示这是第一页 
        WritableSheet sheet=excel.createSheet(sheetName,0);
        
        WritableCellFormat centerFormat = new WritableCellFormat();  
        centerFormat.setAlignment(jxl.format.Alignment.CENTRE);
        
        Set<String> keys = titles.keySet();
        int i = 1;
        for(String key:keys){
            Label label = new Label(i, 0, key);
            label.setCellFormat(centerFormat);
            sheet.addCell(label);//设置合并单元格的标题
            int j = i;
            List<String> subTitles = titles.get(key);
            for(String subTitle:subTitles){
                sheet.addCell(new Label(i++, 1, subTitle));
            }
            sheet.mergeCells(j, 0, i-1, 0);//合并单元格
        }
        
        sheet.addCell(new Label(0, 1, "日期"));
        
        //写入数据
        for(int k=0; k<data.size(); k++){
            List<Object> item = data.get(k);
            for(int j=0; j<item.size(); j++){
                sheet.addCell(new Label(j, k+2, item.get(j).toString()));
            }
        }
        excel.write();
        excel.close();        
    }
}
