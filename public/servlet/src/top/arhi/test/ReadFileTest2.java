package top.arhi.test;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import top.arhi.util.FileUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileTest2 {
    public static void main(String[] args) throws WriteException, IOException {
        File file = new File("D:\\BaiduNetdiskDownload\\frontend\\itcast");
        List<String> files = FileUtil.findFiles(file, new ArrayList<String>());
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\BaiduNetdiskDownload\\frontend\\itcast\\itcast.txt"));
        for (int i = 0; i < files.size(); i++) {
            bw.write(files.get(i));
            bw.newLine();
        }
        bw.close();
    }

    public static void testJXLExcel() throws IOException, WriteException {
        //创建工作簿并指定名称
        WritableWorkbook workbook = Workbook.createWorkbook(new File("D:\\test.xls"));
        //创建工作表指定工作表名称与起始位置
        WritableSheet sheet1 = workbook.createSheet("sheet1", 0);
        Label label = new Label(0,0,"HelloWorld");
        sheet1.addCell(label);
        workbook.write();
        workbook.close();
    }
}
