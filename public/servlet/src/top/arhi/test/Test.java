package top.arhi.test;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
public class Test {
    public static void main(String[] argv) throws Exception {
        Document document = new Document();
        //https://www.e-iceblue.cn/Introduce/Spire-Doc-JAVA.html
        document.loadFromFile("E:\\develop\\gitee.com\\repo\\funplus\\public\\servlet\\web\\upload\\doc\\test.docx");//源文件
        //保存结果文件
        document.saveToFile("E:\\develop\\gitee.com\\repo\\funplus\\public\\servlet\\web\\upload\\doc\\test.pdf", FileFormat.PDF);
    }
}
