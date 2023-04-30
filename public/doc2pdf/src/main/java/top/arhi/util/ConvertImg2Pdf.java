package top.arhi.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

public class ConvertImg2Pdf {
    public static void imagesToPdf(String imagesPath,String destPath) {
        try {
            // 创建一个document对象。
            Document document = new Document();
            document.setMargins(0, 0, 0, 0);
            // 创建一个PdfWriter实例，
            PdfWriter.getInstance(document, new FileOutputStream(destPath));
            // 打开文档
            document.open();
            // 在文档中增加图片。
            File files = new File(imagesPath);
            String[] images = files.list();
            int len = images.length;

            for (int i = 0; i < len; i++) {
                if (images[i].toLowerCase().endsWith(".png")) {
                    String temp = imagesPath + "\\" + images[i];
                    Image img = Image.getInstance(temp);
                    img.setAlignment(Image.ALIGN_CENTER);
                    // 根据图片大小设置页面，一定要先设置页面，再newPage（），否则无效
                    document.setPageSize(new Rectangle(img.getWidth(), img
                            .getHeight()));
                    document.newPage();
                    document.add(img);
                }
            }
            // 关闭文档。
            document.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}