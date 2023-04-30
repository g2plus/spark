package top.arhi.test;

import java.io.*;

public class ReadFileTest3 {
    public ReadFileTest3() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {

//        String[] test={"/test/0001-test.mp4","/test/0002-test.mp4"};
//
//        for (int i = 0; i < test.length; i++) {
//
//            String str=test[i].substring(0,test[i].lastIndexOf("/")+1)+String.format("%02d",  (i + 1))+"-"+test[i].substring(test[i].lastIndexOf("/") + 1).split("-")[1];
//            System.out.println(str);
//        }

        BufferedReader br = new BufferedReader(new FileReader("D:\\BaiduNetdiskDownload\\frontend\\itcast\\itcast.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\BaiduNetdiskDownload\\frontend\\itcast\\itcast-new.txt"));
        String line = null;
        int cnt = 1;
        while ((line = br.readLine()) != null) {
            String[] split = line.substring(line.lastIndexOf("/") + 1).split("-");
            String str = "";
            if (split.length == 2) {
                str =line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", cnt++) + "-" + line.substring(line.lastIndexOf("/") + 1).split("-")[1];
            } else if (split.length == 3) {
                str = line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", cnt++) + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[1] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[2];
            } else if (split.length == 4) {
                str = line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", cnt++) + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[1] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[2] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[3];
            } else if (split.length == 5) {
                str = line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", cnt++) + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[1] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[2] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[3] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[4];
            } else if (split.length == 6) {
                str = line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", cnt++) + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[1] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[2] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[3] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[4] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[5];
            } else if (split.length == 7) {
                str = line.substring(0, line.lastIndexOf("/") + 1) + String.format("%02d", cnt++) + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[1] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[2] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[3] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[4] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[5] + "-"
                        + line.substring(line.lastIndexOf("/") + 1).split("-")[6];
            }
            bw.write(str);
            bw.newLine();
        }

        bw.close();
        br.close();

    }


}
