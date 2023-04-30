package cn.fiesacyum;

import java.io.File;
import java.util.*;

public abstract class FileUtil {

    public  static void removeFile(File file) {
        if (file == null||!file.exists()) {
            System.out.println("File is not existed!");
            return;
        }
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    if (subFile.isDirectory()) {
                        if (subFile.listFiles().length != 0) {
                            removeFile(subFile);
                        } else {
                            subFile.delete();
                            System.out.println(subFile.getAbsolutePath() + "is deleted!");
                        }
                    }
                }
            }
        }else{
            file.delete();
        }
    }

    public static List<String> findFiles(File file, List<String> list) {
        if(list==null){
            list=new ArrayList<String>();
        }
        if (file == null||!file.exists()) {
            return null;
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    if (subFile.isFile()) {
                        list.add(subFile.getAbsolutePath());
                    } else {
                       findFiles(subFile,list);
                    }
                }
            }
        }else{
            list.add(file.getAbsolutePath());
        }
        return list;
    }

    public static Long getSize(File file,long size) {
        if (file == null||!file.exists()) {
            return null;
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    if (subFile.isFile()) {
                        size += subFile.length();
                    } else {
                        getSize(subFile,size);
                    }
                }
            }
        }else{
            size=file.length();
        }
        return size;
    }

    public  static Map<String, Integer> countFile(File file, List<String> list, Map<String,Integer> map) {

        if(list==null){
            list = new ArrayList<String>();
        }
        if(map==null){
            map=new HashMap<String,Integer>();
        }
        if (file == null||!file.exists()) {
            return null;
        }
        List<String> files = FileUtil.findFiles(file,list);
        for (int i = 0; i < files.size(); i++) {
            String osuffix = files.get(i).substring(files.get(i).lastIndexOf(".") + 1, files.get(i).length());
            int cnt = 1;
            boolean flag = false;
            for (String s : files) {
                String isuffix = s.substring(s.lastIndexOf(".") + 1, s.length());
                if (Objects.equals(osuffix, isuffix)) {
                    cnt++;
                }
            }
            map.put(osuffix, cnt-1);
        }
        return map;
    }

    public static List<String> findEmptyDir(File file,List<String> list) {
        if(list==null){
            list=new ArrayList<String>();
        }
        if (file == null||!file.exists()) {
            return null;
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    if(subFile.isFile()){
                        continue;
                    }else if(subFile.isDirectory()&&subFile.listFiles().length==0){
                        list.add(subFile.getAbsolutePath());
                    }else{
                        findEmptyDir(subFile,list);
                    }
                }
            }
        }else{
            System.out.println("It's a file,not a directory");
        }
        return list;
    }

    public static List<String> findSameFile(File file){
        return null;
    }

    public static void viewFiles(File file) {
        //显示当前文件夹下内容
        File[] files = file.listFiles();
        for (File myFile : files) {
            System.out.println(myFile.toString());
        }
    }

    public static List<String> viewFilesRecursivly(File file,List<String> list) {
        File[] files = file.listFiles();
        for (File myFile : files) {
            if (myFile.isDirectory()) {
                viewFilesRecursivly(myFile,list);
            } else {
                list.add(myFile.getAbsolutePath());
            }
        }
        return list;
    }
}
