package top.arhi.test;

import top.arhi.util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class RemoveEmptyFileTest {
    public static void main(String[] args) {
        List<String> emptyDir = FileUtil.findEmptyDir(new File("D:\\BaiduNetdiskDownload\\frontend\\itcast"), new ArrayList<String>());
        emptyDir.stream().forEach(
                new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        File file=new File(s);
                        file.delete();
                    }
                }
        );
    }
}
