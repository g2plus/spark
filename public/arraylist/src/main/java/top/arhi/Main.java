package top.arhi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> list= new ArrayList<String>();
        System.out.println(list.size());
        list.add(0,"Hello World!");
        //在某个索引的位置进行插入数值。索引必须存在，不存在的索引将抛出异常IndexOutOfBoundsException。
        Object[] objects = list.toArray();
        Stream<Object> obj = Stream.of(objects);
        obj.forEach(o-> System.out.println(o));
        list.add(0,"Hello");
        System.out.println(list.toString());
        //程序运行完毕后，释放内存数据丢失。
    }
}
