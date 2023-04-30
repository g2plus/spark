package top.arhi.test;

import java.io.*;
import java.util.function.Consumer;

public class RenameFileTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\BaiduNetdiskDownload\\frontend\\itcast\\itcast.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\BaiduNetdiskDownload\\frontend\\itcast\\itcast-new.txt"));
        String line = null;
        //获取文件的第一行并写入文件
        String firstLine = br.readLine();
        String prefix = firstLine.substring(0, firstLine.lastIndexOf("/") + 1);
        bw.write(prefix+String.format("%02d", 1) +
                firstLine.substring((prefix+firstLine.substring(firstLine.lastIndexOf("/") + 1).split("-")[0]).length()));
        bw.newLine();
        int a = 2;
        while ((line = br.readLine()) != null) {
            String temp = line.substring(0, line.lastIndexOf("/") + 1);
            String str = "";
            if (temp.equals(prefix)) {
                str = line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", a++) +
                        line.substring((prefix+line.substring(line.lastIndexOf("/") + 1).split("-")[0]).length());
            } else {
                a = 1;
                prefix = temp;
                str = line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", a++) +
                        line.substring((prefix+line.substring(line.lastIndexOf("/") + 1).split("-")[0]).length());
            }
            bw.write(str);
            bw.newLine();
        }
        bw.close();
        br.close();
    }
}

