package top.arhi.test;

import org.junit.Test;
import top.arhi.domain.Zip;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


public class ZipTest {


    public static void main(String[] args) throws IOException {
        //testZip();
        testUnZip();
    }
    public static void testZip() throws IOException {
        //创建文件对象
        String source="E:\\develop\\gitee.com\\repo\\funplus\\public\\servlet\\web\\upload\\img\\beauty.jpg";
        File sourceFile = new File(source);
        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        System.out.println(source.substring(0,source.lastIndexOf("."))+".zip");
        File dir = new File(sourceFile.getParentFile().getParentFile().getAbsolutePath()+File.separator+"zip");
        if(!(dir.exists()&&dir.isDirectory())){
            dir.mkdirs();
        }
        File targetFile=new File(dir +File.separator+ sourceFile.getName().split("\\.")[0] +".zip");
        ZipOutputStream zipOutputStream=new ZipOutputStream(new FileOutputStream(targetFile));
        ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
        zipOutputStream.putNextEntry(zipEntry);
        byte[] buffer=new byte[1024*8];
        int length;
        while((length=fileInputStream.read(buffer))!=-1){
            zipOutputStream.write(buffer,0,length);
        }
        zipOutputStream.close();
        fileInputStream.close();
    }

    public static void testUnZip() throws IOException {
        String sourcePath="E:\\develop\\gitee.com\\repo\\funplus\\public\\servlet\\web\\upload\\zip\\beauty.zip";
        String targetPath="E:\\develop\\gitee.com\\repo\\funplus\\public\\servlet\\web\\upload\\img\\beauty.png";
        ZipInputStream zis=new ZipInputStream(new FileInputStream(sourcePath));
        ZipEntry zipEntry = zis.getNextEntry();
        FileOutputStream fos=new FileOutputStream(targetPath);
        if(zipEntry!=null){
            byte[] buffer=new byte[1024*8];
            int length;
            while((length=zis.read(buffer))!=-1){
                fos.write(buffer,0,length);
            }
            fos.close();
            zis.closeEntry();
            zis.close();
        }
    }
}
