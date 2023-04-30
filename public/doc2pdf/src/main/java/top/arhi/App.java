package top.arhi;

import com.convertapi.client.Config;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.Param;
import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.filetypes.FileType;
import com.groupdocs.conversion.options.convert.ConvertOptions;
import com.groupdocs.watermark.Watermarker;
import com.groupdocs.watermark.search.PossibleWatermarkCollection;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import top.arhi.util.ConvertImg2Pdf;
import top.arhi.util.ConvertPdf2Img;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

import java.io.File;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
//        convertPdf2Img("E:\\develop\\gitee.com\\repo\\spark\\public\\DocToPdf\\src\\main\\resources\\output2.pdf",
//                "E:\\home\\output\\",
//                "png",
//                100);
        convertImg2pdf("E:\\home\\pdf","广州版英语 3-6年级单词汇总-默写版.pdf");
    }

    private static void convertWord2PdfByPOI() {
        //存在的缺陷：图片丢失，格式错乱。
        //开源
        try {
            InputStream docFile = new FileInputStream(new File("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\funplus.docx"));
            XWPFDocument doc = new XWPFDocument(docFile);
            PdfOptions pdfOptions = PdfOptions.create();
            OutputStream out = new FileOutputStream(new File("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\funplus.pdf"));
            PdfConverter.getInstance().convert(doc, out, pdfOptions);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void convertWord2PdfByGroupdocs() throws FileNotFoundException {
        //收费
        //it performs well,but data loss happens.
        // Load the source DOC file to be converted
        Converter converter = new Converter(new FileInputStream(new File("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\test.docx")));
        // Get the convert options ready for the target PDF format
        ConvertOptions convertOptions = FileType.fromExtension("pdf").getConvertOptions();
        // Convert to PDF format
        converter.convert(new FileOutputStream(new File("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\test3.pdf")), convertOptions);

    }

    private static void convertWord2PdfByConvertApi() throws IOException, ExecutionException, InterruptedException {
        //收费
        //服务订阅网址:https://www.convertapi.com/
        //缺点：本地运行,不知道文件何时转换成功
        Config.setDefaultSecret("WIOTfIe6o9eHXqrj");
        //Set input and output formats and pass file parameter.
        //Word to PDF API. Read more https://www.convertapi.com/docx-to-pdf
        ConvertApi.convert("pdf", "pdf",
                new Param("file", Paths.get("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\input.pdf"))
        ).get().saveFile(Paths.get("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\output.pdf")).get();
    }

    private static void removeWaterMark(){
        //收费
        //https://blog.conholdate.com/2021/05/28/remove-watermarks-from-pdf-documents-using-java/
        // Create an instance
        Watermarker watermarker = new Watermarker("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\watermark.pdf");
        // Search all possible watermarks
        PossibleWatermarkCollection possibleWatermarks = watermarker.search();
        // Remove all found watermarks
        possibleWatermarks.clear();
        // Save updated file
        watermarker.save("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\out.pdf");
        watermarker.close();
    }

    private static void convertPdf2Img(String source,String targetDir,String imgType,int dpi) throws IOException {
        File pdfFile = new File(source);
        if(!pdfFile.exists()){
            throw new NoSuchFileException(pdfFile.getAbsolutePath());
        }
        File dir = new File(targetDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        ConvertImg2Pdf.PDFToImg(pdfFile, targetDir, pdfFile.getName().split("\\.")[0]+"-",imgType, dpi);
    }

    private static void convertImg2pdf(String imagesPath,String destPath){
        ConveterImg2pdf.imagesToPdf(imagesPath,destPath);
    }

    private static void removeImgWaterMark(){
        //收费
        //pdf去水印的方法，转换为图片，然后使用OpenCV进行图像处理，去除水印。
        // Create an instance
        Watermarker watermarker = new Watermarker("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\watermark.pdf");

        // Search all possible watermarks
        PossibleWatermarkCollection possibleWatermarks = watermarker.search();

        // Remove all image watermarks
        for (int i = possibleWatermarks.getCount() - 1; i >= 0; i--)
        {
            if(possibleWatermarks.get_Item(i).getImageData() != null)
            {
                possibleWatermarks.removeAt(i);
            }
        }

        // Save updated document
        watermarker.save("E:\\develop\\gitee.com\\repo\\funplus\\public\\DocToPdf\\src\\main\\resources\\out.pdf");

        watermarker.close();

    }

    //图片去除水印
    private static void removeWatermarkOnImg(){
        //TODO 图片去除水印javacv
    }
}
