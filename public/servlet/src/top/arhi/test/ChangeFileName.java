package top.arhi.test;

import org.junit.Test;

import java.io.*;
import java.net.URLDecoder;

/**
 * @author e2607
 */
public class ChangeFileName {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\home\\img");
        if(file.exists()){
            File[] files = file.listFiles();
            FileInputStream fis=null;
            FileOutputStream fos=null;
            String format="";
            if(files!=null) {
                for (int i=0;i<files.length;i++) {
                    String src =files[i].getAbsolutePath();
                    src = URLDecoder.decode(src, "utf-8");
                    format = "%0"+Integer.valueOf(files.length).toString().length()+"d";
                    fis= new FileInputStream(src);
                    fos = new FileOutputStream(new File("E:\\home\\img\\" + String.format(format,i+1) +"."+src.split("\\.")[1]));
                    int j=-1;
                    byte[] bytes=new byte[1024];
                    while((j=fis.read(bytes))!=-1){
                        fos.write(bytes,0,bytes.length);
                    }
                    fos.close();
                    fis.close();
                }
            }
        }
    }
}
