package top.arhi.test;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

public class ReplaceTest {

    public static String replaceStr(String ins){
        String s=ins;
        String temp="        ";
        int point=0;
        StringBuilder stringBuilder=new StringBuilder();
        while(point<s.length()){
            if(point<temp.length()){
                point++;
                continue;
            }
            stringBuilder.append(s.charAt(point));
            point++;
        }
        return stringBuilder.toString();
    }

    @Test
    public void method() throws IOException {
        File file=new File("e:\\home\\html");

        File[] files=file.listFiles();
       if(files!=null&&files.length!=0){
           for (File subFile : files) {
              BufferedReader reader= new BufferedReader(new FileReader(subFile));
              ArrayList<String> list=new ArrayList<String>(200);
              String str="";
              while((str=reader.readLine())!=null){
                  String ins = str ;
                  String out=replaceStr(ins);
                  list.add(out);
              }
              reader.close();
              BufferedWriter writer=new BufferedWriter(new FileWriter(subFile,false));
               for (String s : list) {
                   writer.write(s);
                   writer.newLine();
                   writer.flush();
               }
               writer.close();
           }
       }
    }
}
