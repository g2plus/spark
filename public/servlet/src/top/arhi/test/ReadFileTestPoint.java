package top.arhi.test;

import java.io.*;

public class ReadFileTestPoint {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\BaiduNetdiskDownload\\frontend\\itcast\\itcast.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\BaiduNetdiskDownload\\frontend\\itcast\\itcast-new.txt"));
        String line = null;
        int cnt = 1;
        while ((line = br.readLine()) != null) {
            String[] split = line.substring(line.lastIndexOf("/") + 1).split("\\.");
            String str = "";
            str = line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", cnt++) + "-" +split[1]
                    + "." + split[2];

            bw.write(str);
            bw.newLine();
        }
        bw.close();
        br.close();
    }
}
