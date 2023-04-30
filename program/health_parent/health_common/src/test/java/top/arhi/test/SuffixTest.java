package top.arhi.test;

public class SuffixTest {
    public static void main(String[] args) {
        String fileName="a605288582.jpg";
        int index = fileName.lastIndexOf(".");
        System.out.println(fileName.substring(index));
    }
}
