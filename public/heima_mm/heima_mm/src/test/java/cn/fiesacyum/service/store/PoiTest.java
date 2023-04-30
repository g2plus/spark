package cn.fiesacyum.service.store;

import cn.fiesacyum.domain.store.Question;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PoiTest {
    @Test
    public void testPoiWrite() throws IOException {
        //1.获取到对应的Excel文件，工作簿文件
        XSSFWorkbook wb=new XSSFWorkbook();
        //2.创建工作表
        Sheet sheet=wb.createSheet("sheet1");//创建工作表，并设置工作表的名称

        //3.创建工作表中的行对象
        Row row = sheet.createRow(5); //int i->index 索引从0开始
        //4.创建工作表中行中的列对象
        Cell cell = row.createCell(0);
        //5.在单元格中写入数据
        cell.setCellValue("Hello Poi!");//数据写入到第6行的第二列A6.

        //创建一个文件对象，作为excel文件内容的输出文件
        File f=new File("test.xlsx");//此文件将被保存在项目根目录下
        //输出时通过流的形式对外输出，包装对应的目标文件
        OutputStream os=new FileOutputStream(f);
        //将数据写入os封装的文件中。
        wb.write(os);
        //释放内存。
        wb.close();
        os.close();
    }

    @Test
    public void testPoiRread() throws IOException {
        //1.获取到要读的文件工作簿对象
        XSSFWorkbook wb=new XSSFWorkbook("test.xlsx");
        //2.获取工作表
        Sheet sheet = wb.getSheetAt(0);
        //3.获取行
        Row row = sheet.getRow(5);
        //4.获取列
        Cell cell1 = row.getCell(0);//A6
        Cell cell2 = row.getCell(1);//B6
        Cell cell3 = row.getCell(2);//C6
        //5.根据数据的类型获取数据(需要明确数据的类型才能获取到数据)
        String data1 = cell1.getStringCellValue();
        double data2 = cell2.getNumericCellValue();
        String data3 = cell3.getStringCellValue();
        System.out.println(data1);//Hello Poi!
        System.out.println(data2);//30.0
        System.out.println(data3);//true
        wb.close();
    }

    @Test
    public void testProjectPoi() throws IOException {
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

        //制作数据区 Ctrl+Shift+Alt+J
        List<Question> questionList = new ArrayList<>();
        Question question1=new Question();
        question1.setId("1-1");
        question1.setCompanyId("1-2");
        question1.setCatalogId("1-3");
        question1.setRemark("1-4");
        question1.setSubject("1-5");
        question1.setPicture("1-6");
        question1.setAnalysis("1-7");
        question1.setType("1-8");
        question1.setDifficulty("1-9");
        question1.setIsClassic("1-10");
        question1.setState("1-11");
        question1.setReviewStatus("1-12");

        Question question2=new Question();
        question2.setId("1-1");
        question2.setCompanyId("1-2");
        question2.setCatalogId("1-3");
        question2.setRemark("1-4");
        question2.setSubject("1-5");
        question2.setPicture("1-6");
        question2.setAnalysis("1-7");
        question2.setType("1-8");
        question2.setDifficulty("1-9");
        question2.setIsClassic("1-10");
        question2.setState("1-11");
        question2.setReviewStatus("1-12");

        questionList.add(question1);
        questionList.add(question2);

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

        //创建一个文件对象，作为excel文件内容的输出文件
        File f=new File("testProjectPoi.xlsx");//此文件将被保存在项目根目录下
        //输出时通过流的形式对外输出，包装对应的目标文件
        OutputStream os=new FileOutputStream(f);
        //将数据写入os封装的文件中。
        wb.write(os);
        //释放内存。
        wb.close();
        os.close();
    }
}
