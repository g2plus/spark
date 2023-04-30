package cn.fiesacyum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileTest {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        File file = new File("D:\\BaiduNetdiskDownload\\frontend\\itcast\\Web前端");
        List<String> files = FileUtil.viewFilesRecursivly(file,list);
        //FileWriter fw = new FileWriter(new File("D:\\BaiduNetdiskDownload\\frontend\\file.txt"));
        //BufferedWriter bw=new BufferedWriter(fw);
        //for (String path : files) {
        //    if(path.endsWith(".rar")||path.endsWith(".zip"))
        //    bw.write(path);
        //    bw.newLine();
        //}
        //bw.close();
        for (String path : files) {
            if(path.endsWith(".rar")||path.endsWith(".zip")){
                System.out.println(path);
            }
        }
    }
}
