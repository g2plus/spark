package cn.fiesacyum.utils;

import cn.fiesacyum.domain.store.Question;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public class FileUtil {
    public static ByteArrayOutputStream exportFile(List<Question> list) throws IOException {
        //1.获取到工作簿对象
        XSSFWorkbook wb=new XSSFWorkbook();
        //2.创建工作表
        Sheet sheet = wb.createSheet("testProjectPoi");

        //通用配置设置列宽。
        sheet.setColumnWidth(1,200);//需要调试才能确定
        CellStyle cs=wb.createCellStyle();
        CellStyle dataCs=wb.createCellStyle();
        //从所在单元格开始合并单元格。
        //制作合并单元格
        sheet.addMergedRegion(new CellRangeAddress(1,1,1,12));

        //数据写入到行索引1列索引为1的单元
        Row row_1=sheet.createRow(1);//索引为1的行
        Cell cell_1_1=row_1.createCell(1);
        cell_1_1.setCellValue("在线试题导出信息");

        //通过wb创建样式，并对合并单元格设置样式
        cs.setAlignment(HorizontalAlignment.CENTER);//设置水平方向居中对齐
        cs.setVerticalAlignment(VerticalAlignment.CENTER);//设置垂直方向居中对齐
        cell_1_1.setCellStyle(cs);

        //设置数据域的单元格样式
        dataCs.setAlignment(HorizontalAlignment.CENTER);


        //制作栏目单元格
        String[][] fields={{"题目","所属公司ID", "所属目录ID", "题目简介", "题干描述", "题干配图", "题目分析",
                "题目类型", "题目难度", "是否经典题", "题目状态", "审核状态"},{"id","companyId","catalogId","remark",
                "subject","picture","analysis","type","difficulty","isClassic","state","reviewStatus" }};

        //二维数组的遍历
        for(int i=0;i<fields.length;i++){
            Row row_temp=sheet.createRow(2+i);
            System.out.println(row_temp);
            for(int j=0;j<fields[i].length;j++){
                Cell cell_temp=row_temp.createCell(1+j);
                cell_temp.setCellValue(fields[i][j]);
                System.out.println(cell_temp);
                cell_temp.setCellStyle(dataCs);
            }
        }
        List<Question> questionList = list;
        int row_index=0;
        for(Question question:questionList){
            Row row_temp=sheet.createRow(4+row_index++);
            int cell_index=0;

            Cell cell_data_Id=row_temp.createCell(1+cell_index++);
            cell_data_Id.setCellValue(question.getId());
            cell_data_Id.setCellStyle(dataCs);

            Cell cell_data_CompanyId=row_temp.createCell(1+cell_index++);
            cell_data_CompanyId.setCellValue(question.getCompanyId());
            cell_data_CompanyId.setCellStyle(dataCs);

            Cell cell_data_CatalogId=row_temp.createCell(1+cell_index++);
            cell_data_CatalogId.setCellValue(question.getCatalogId());
            cell_data_CatalogId.setCellStyle(dataCs);

            Cell cell_data_Remark=row_temp.createCell(1+cell_index++);
            cell_data_Remark.setCellValue(question.getRemark());
            cell_data_Remark.setCellStyle(dataCs);

            Cell cell_data_Subject=row_temp.createCell(1+cell_index++);
            cell_data_Subject.setCellValue(question.getSubject());
            cell_data_Subject.setCellStyle(dataCs);

            Cell cell_data_Picture=row_temp.createCell(1+cell_index++);
            cell_data_Picture.setCellValue(question.getPicture());
            cell_data_Picture.setCellStyle(dataCs);

            Cell cell_data_Analysis=row_temp.createCell(1+cell_index++);
            cell_data_Analysis.setCellValue(question.getAnalysis());
            cell_data_Analysis.setCellStyle(dataCs);

            Cell cell_data_Type=row_temp.createCell(1+cell_index++);
            cell_data_Type.setCellValue(question.getType());
            cell_data_Type.setCellStyle(dataCs);

            Cell cell_data_Difficulty=row_temp.createCell(1+cell_index++);
            cell_data_Difficulty.setCellValue(question.getDifficulty());
            cell_data_Picture.setCellStyle(dataCs);

            Cell cell_data_IsClassic=row_temp.createCell(1+cell_index++);
            cell_data_IsClassic.setCellValue(question.getIsClassic());
            cell_data_IsClassic.setCellStyle(dataCs);

            Cell cell_data_State=row_temp.createCell(1+cell_index++);
            cell_data_State.setCellValue(question.getState());
            cell_data_State.setCellStyle(dataCs);

            Cell cell_data_ReviewStatus=row_temp.createCell(1+cell_index++);
            cell_data_ReviewStatus.setCellValue(question.getReviewStatus());
            cell_data_ReviewStatus.setCellStyle(dataCs);
        }

        ByteArrayOutputStream os=new ByteArrayOutputStream();
        wb.write(os);
        wb.close();
        return os;
    }

}
