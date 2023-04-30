package top.arhi.thread;

import top.arhi.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class ReadFile implements Runnable {
    @Override
    public void run() {
        File file=new File("E:\\home\\img");
        List<String> list=new ArrayList<String>();
        long size=0L;
        List<String> files = FileUtil.findFiles(file,list);
        files.stream().forEach(s->System.out.println(Thread.currentThread()
                .getContextClassLoader()+
                ":"+Thread.currentThread().getName()+":"+s));
        System.out.println(Thread.currentThread().getName()+":"+FileUtil.getSize(file,size));
    }
}
