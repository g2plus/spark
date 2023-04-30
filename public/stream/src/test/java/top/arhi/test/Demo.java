package top.arhi.test;

import org.junit.Test;
import top.arhi.domain.Dish;
import top.arhi.domain.User;
import top.arhi.enums.Type;
import top.arhi.util.DateUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static top.arhi.util.DateUtils.YYYY_MM_DD;

public class Demo {

    @Test
    public void testCreatingStream() {

        //Stream流只能使用一次
        //数组
        String[] str = {"hello", "world"};
        Arrays.stream(str).forEach(s -> System.out.println(s));

        //单列集合
        List<String> list = new ArrayList<>();
        //数组转换成集合
        Arrays.asList(str);

        //控制台无输出
        list.stream().forEach(s -> System.out.println(s));

        //控制台无输出
        //list转集合
        String[] strings = list.toArray(new String[list.size()]);
        Arrays.stream(strings).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

    }


    /**
     * stream流过滤
     */
    @Test
    public void testFilter() throws ParseException {

        List<User> users = new ArrayList<User>(10);

        User user1 = new User().builder().id("id001").name("船长").age(23).gender("1").dob(DateUtils.parseDate("1999-10-23", YYYY_MM_DD)).build();
        User user2 = new User().builder().id("id002").name("阿卡丽").age(16).gender("0").dob(DateUtils.parseDate("2006-10-23", YYYY_MM_DD)).build();
        User user3 = new User().builder().id("id003").name("凯南").age(16).gender("1").dob(DateUtils.parseDate("2006-11-11", YYYY_MM_DD)).build();
        User user4 = new User().builder().id("id004").name("沙皇").age(17).gender("1").dob(DateUtils.parseDate("2005-01-18", YYYY_MM_DD)).build();
        User user5 = new User().builder().id("id005").name("妖姬").age(24).gender("0").dob(DateUtils.parseDate("1998-01-18", YYYY_MM_DD)).build();
        User user6 = new User().builder().id("id006").name("剑魔").age(18).gender("1").dob(DateUtils.parseDate("2004-10-23", YYYY_MM_DD)).build();
        User user7 = new User().builder().id("id007").name("猫咪").age(18).gender("0").dob(DateUtils.parseDate("2004-06-23", YYYY_MM_DD)).build();
        User user8 = new User().builder().id("id008").name("青刚影").age(20).gender("0").dob(DateUtils.parseDate("2002-10-23", YYYY_MM_DD)).build();
        User user9 = new User().builder().id("id009").name("盲僧").age(24).gender("1").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();
        User user10 = new User().builder().id("id010").name("露露").age(24).gender("0").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);


        //过滤年龄少于18岁的 使用stream流不用影响原list,使用collect收集器进行收集查找效果
        List<User> collect = users.stream().filter(new Predicate<User>() {
            @Override
            public boolean test(User user) {
                return user.getAge() >= 18;
            }
        }).collect(Collectors.toList());


        //使用stream流不用影响原list
        users.stream().forEach(user -> System.out.println(user));
        System.out.println("----------------------------------------------");
        collect.stream().forEach(user -> System.out.println(user));

    }


    /**
     * stream流list转换Map
     */
    @Test
    public void testMap() throws ParseException {

        List<User> users = new ArrayList<User>(10);

        User user1 = new User().builder().id("id001").name("船长").age(23).gender("1").dob(DateUtils.parseDate("1999-10-23", YYYY_MM_DD)).build();
        User user2 = new User().builder().id("id002").name("阿卡丽").age(16).gender("0").dob(DateUtils.parseDate("2006-10-23", YYYY_MM_DD)).build();
        User user3 = new User().builder().id("id003").name("凯南").age(16).gender("1").dob(DateUtils.parseDate("2006-11-11", YYYY_MM_DD)).build();
        User user4 = new User().builder().id("id004").name("沙皇").age(17).gender("1").dob(DateUtils.parseDate("2005-01-18", YYYY_MM_DD)).build();
        User user5 = new User().builder().id("id005").name("妖姬").age(24).gender("0").dob(DateUtils.parseDate("1998-01-18", YYYY_MM_DD)).build();
        User user6 = new User().builder().id("id006").name("剑魔").age(18).gender("1").dob(DateUtils.parseDate("2004-10-23", YYYY_MM_DD)).build();
        User user7 = new User().builder().id("id007").name("猫咪").age(18).gender("0").dob(DateUtils.parseDate("2004-06-23", YYYY_MM_DD)).build();
        User user8 = new User().builder().id("id008").name("青刚影").age(20).gender("0").dob(DateUtils.parseDate("2002-10-23", YYYY_MM_DD)).build();
        User user9 = new User().builder().id("id009").name("盲僧").age(24).gender("1").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();
        User user10 = new User().builder().id("id010").name("露露").age(24).gender("0").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);

        //以用户ID作为key,user作为value
        Map<String, User> collect1 = users.stream().collect(Collectors.toMap(User::getId, Function.identity(), (key1, key2) -> key2));

        User id0001User = collect1.get("id001");

        System.out.println(id0001User);


        System.out.println("-----------------------------------------");

        collect1.forEach(new BiConsumer<String, User>() {
            @Override
            public void accept(String s, User user) {
                System.out.println(collect1.get(s));
            }
        });


        Map<String, String> collect2 = users.stream().collect(Collectors.toMap(User::getId, User::getName, (key1, key2) -> key2));

        String id005Name = collect2.get("id005");

        System.out.println("----------------------------------------");
        System.out.println(id005Name);


    }


    /**
     * stream流分组(根据年龄)
     */
    @Test
    public void testGroupBy() throws ParseException {

        List<User> users = new ArrayList<User>(10);

        User user1 = new User().builder().id("id001").name("船长").age(23).gender("1").dob(DateUtils.parseDate("1999-10-23", YYYY_MM_DD)).build();
        User user2 = new User().builder().id("id002").name("阿卡丽").age(16).gender("0").dob(DateUtils.parseDate("2006-10-23", YYYY_MM_DD)).build();
        User user3 = new User().builder().id("id003").name("凯南").age(16).gender("1").dob(DateUtils.parseDate("2006-11-11", YYYY_MM_DD)).build();
        User user4 = new User().builder().id("id004").name("沙皇").age(17).gender("1").dob(DateUtils.parseDate("2005-01-18", YYYY_MM_DD)).build();
        User user5 = new User().builder().id("id005").name("妖姬").age(24).gender("0").dob(DateUtils.parseDate("1998-01-18", YYYY_MM_DD)).build();
        User user6 = new User().builder().id("id006").name("剑魔").age(18).gender("1").dob(DateUtils.parseDate("2004-10-23", YYYY_MM_DD)).build();
        User user7 = new User().builder().id("id007").name("猫咪").age(18).gender("0").dob(DateUtils.parseDate("2004-06-23", YYYY_MM_DD)).build();
        User user8 = new User().builder().id("id008").name("青刚影").age(20).gender("0").dob(DateUtils.parseDate("2002-10-23", YYYY_MM_DD)).build();
        User user9 = new User().builder().id("id009").name("盲僧").age(24).gender("1").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();
        User user10 = new User().builder().id("id010").name("露露").age(24).gender("0").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);


        Map<Integer, List<User>> collect = users.stream().collect(Collectors.groupingBy(User::getAge));


        //根据年龄进行分组
        collect.forEach(new BiConsumer<Integer, List<User>>() {
            @Override
            public void accept(Integer age, List<User> users) {
                System.out.println(collect.get(age));
            }
        });


    }


    /***
     * stream流根据类的某个字段进行分组
     */
    @Test
    public void testGroupByField(){

        //参考地址:https://www.cnblogs.com/baidawei/p/9283954.html
        List<Dish> dishes = Arrays.asList(
                new Dish("猪肉炖粉条", false, 800, Type.MEAT),
                new Dish("小炒牛肉", false, 700, Type.MEAT),
                new Dish("宫保鸡丁", false, 400, Type.MEAT),
                new Dish("地三鲜", true, 530, Type.OTHER),
                new Dish("水煮菠菜", true, 350, Type.OTHER),
                new Dish("拔丝地瓜", true, 120, Type.OTHER),
                new Dish("火山下雪", true, 550, Type.OTHER),
                new Dish("水煮鱼", false, 330, Type.FISH),
                new Dish("于是乎", false, 450, Type.FISH)
        );

        Map<Object, List<Dish>> collect = dishes.stream().collect(Collectors.groupingBy(new Function<Dish, Object>() {
            @Override
            public Object apply(Dish dish) {
                return dish.getType();
            }
        }));

        //map集合的迭代方式
        //获取keySet集合，使用增强for来遍历
        Set<Object> objects = collect.keySet();
        for (Object object : objects) {
            System.out.println(object+"="+collect.get(object));
        }

        System.out.println("----------------------------------------------");

        //使用stream流来实现
        objects.stream().forEach(object-> System.out.println(object+"="+collect.get(object)));

        //获取keySet集合，使用iterator迭代器来进行遍历
        Iterator<Object> iterator = objects.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj+"="+collect.get(obj));
        }



        System.out.println("-----------------------------------------------");
        //获取entrySet集合,通过增强for来遍历
        Set<Map.Entry<Object, List<Dish>>> entries = collect.entrySet();

        for (Map.Entry<Object, List<Dish>> entry : entries) {
            System.out.println(entry);
        }

        System.out.println("------------------------------------------------");
        //使用迭代器iterator来进行遍历
        Iterator<Map.Entry<Object, List<Dish>>> iterator1 = entries.iterator();
        while(iterator1.hasNext()){
            System.out.println(iterator1.next());
        }

        System.out.println("------------------------------------------------");
        //使用stream流来实现
        entries.stream().forEach(new Consumer<Map.Entry<Object, List<Dish>>>() {
            @Override
            public void accept(Map.Entry<Object, List<Dish>> objectListEntry) {
                System.out.println(objectListEntry);
            }
        });


        collect.forEach(new BiConsumer<Object, List<Dish>>() {
            @Override
            public void accept(Object o, List<Dish> dishes) {
                System.out.println(collect.get(o)+"->"+dishes);
            }
        });
    }


    /**
     * stream流分组(根据出生日期年月分组)
     */
    @Test
    public void testGroupByDateFormat() throws ParseException {

        List<User> users = new ArrayList<User>(10);

        User user1 = new User().builder().id("id001").name("船长").age(23).gender("1").dob(DateUtils.parseDate("1999-10-23", YYYY_MM_DD)).build();
        User user2 = new User().builder().id("id002").name("阿卡丽").age(16).gender("0").dob(DateUtils.parseDate("2006-10-23", YYYY_MM_DD)).build();
        User user3 = new User().builder().id("id003").name("凯南").age(16).gender("1").dob(DateUtils.parseDate("2006-11-11", YYYY_MM_DD)).build();
        User user4 = new User().builder().id("id004").name("沙皇").age(17).gender("1").dob(DateUtils.parseDate("2005-01-18", YYYY_MM_DD)).build();
        User user5 = new User().builder().id("id005").name("妖姬").age(24).gender("0").dob(DateUtils.parseDate("1998-01-18", YYYY_MM_DD)).build();
        User user6 = new User().builder().id("id006").name("剑魔").age(18).gender("1").dob(DateUtils.parseDate("2004-10-23", YYYY_MM_DD)).build();
        User user7 = new User().builder().id("id007").name("猫咪").age(18).gender("0").dob(DateUtils.parseDate("2004-06-23", YYYY_MM_DD)).build();
        User user8 = new User().builder().id("id008").name("青刚影").age(20).gender("0").dob(DateUtils.parseDate("2002-10-23", YYYY_MM_DD)).build();
        User user9 = new User().builder().id("id009").name("盲僧").age(24).gender("1").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();
        User user10 = new User().builder().id("id010").name("露露").age(24).gender("0").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);


        Map<String, List<User>> collect = users.stream().collect(Collectors.groupingBy(user -> new SimpleDateFormat("yyyy-MM").format(user.getDob())));


        //根据年龄进行分组
        collect.forEach(new BiConsumer<String, List<User>>() {
            @Override
            public void accept(String yyyyMM, List<User> users) {
                System.out.println(collect.get(yyyyMM));
            }
        });

    }


    /**
     * stream流排序(根据年龄,成绩)
     */
    @Test
    public void testSort() throws ParseException {

        List<User> users = new ArrayList<User>(10);

        User user1 = new User().builder().id("id001").name("船长").age(23).gender("1").dob(DateUtils.parseDate("1999-10-23", YYYY_MM_DD)).score(100).build();
        User user2 = new User().builder().id("id002").name("阿卡丽").age(16).gender("0").dob(DateUtils.parseDate("2006-10-23", YYYY_MM_DD)).score(98).build();
        User user3 = new User().builder().id("id003").name("凯南").age(16).gender("1").dob(DateUtils.parseDate("2006-11-11", YYYY_MM_DD)).score(88).build();
        User user4 = new User().builder().id("id004").name("沙皇").age(17).gender("1").dob(DateUtils.parseDate("2005-01-18", YYYY_MM_DD)).score(98).build();
        User user5 = new User().builder().id("id005").name("妖姬").age(24).gender("0").dob(DateUtils.parseDate("1998-01-18", YYYY_MM_DD)).score(88).build();
        User user6 = new User().builder().id("id006").name("剑魔").age(18).gender("1").dob(DateUtils.parseDate("2004-10-23", YYYY_MM_DD)).score(100).build();
        User user7 = new User().builder().id("id007").name("猫咪").age(18).gender("0").dob(DateUtils.parseDate("2004-06-23", YYYY_MM_DD)).score(96).build();
        User user8 = new User().builder().id("id008").name("青刚影").age(20).gender("0").dob(DateUtils.parseDate("2002-10-23", YYYY_MM_DD)).score(100).build();
        User user9 = new User().builder().id("id009").name("盲僧").age(24).gender("1").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).score(100).build();
        User user10 = new User().builder().id("id010").name("露露").age(24).gender("0").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).score(90).build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);

        //根据年龄从小到大排序
        List<User> collect1 = users.stream().sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());


        collect1.forEach(user -> System.out.println(user));

        System.out.println("-----------------------------------------------------------------");

        //根据年龄从大到小排序
        List<User> collect2 = users.stream().sorted(Comparator.comparing(User::getAge).reversed())
                .collect(Collectors.toList());

        collect2.forEach(user -> System.out.println(user));


        System.out.println("-----------------------------------------------------------------");


        //现根据年龄排序从大到小排序 如果年龄相同根据分数从高到低排序
        List<User> collect3 = users.stream().sorted(Comparator.comparing(User::getAge)
                .reversed()
                .thenComparing(Comparator.comparing(User::getScore).reversed())
        ).collect(Collectors.toList());

        collect3.stream().forEach(user -> System.out.println(user));


    }


    /**
     * stream流的聚合取值
     */
    @Test
    public void testReduce() {

        //求和
        List<Integer> list = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Integer sum = list.stream().reduce((num1, num2) -> num1 + num2).get();
        System.out.println(sum);


        System.out.println("-------------------------------------");

        //求最小值
        Integer min = list.stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer num1, Integer num2) {
                if (num1 >= num2) {
                    return num2;
                } else {
                    return num1;
                }
            }
        }).get();

        System.out.println(min);

    }


    /***
     * stream流分页
     */
    @Test
    public void testPage() throws ParseException {

        List<User> users = new ArrayList<User>(10);

        User user1 = new User().builder().id("id001").name("船长").age(23).gender("1").dob(DateUtils.parseDate("1999-10-23", YYYY_MM_DD)).build();
        User user2 = new User().builder().id("id002").name("阿卡丽").age(16).gender("0").dob(DateUtils.parseDate("2006-10-23", YYYY_MM_DD)).build();
        User user3 = new User().builder().id("id003").name("凯南").age(16).gender("1").dob(DateUtils.parseDate("2006-11-11", YYYY_MM_DD)).build();
        User user4 = new User().builder().id("id004").name("沙皇").age(17).gender("1").dob(DateUtils.parseDate("2005-01-18", YYYY_MM_DD)).build();
        User user5 = new User().builder().id("id005").name("妖姬").age(24).gender("0").dob(DateUtils.parseDate("1998-01-18", YYYY_MM_DD)).build();
        User user6 = new User().builder().id("id006").name("剑魔").age(18).gender("1").dob(DateUtils.parseDate("2004-10-23", YYYY_MM_DD)).build();
        User user7 = new User().builder().id("id007").name("猫咪").age(18).gender("0").dob(DateUtils.parseDate("2004-06-23", YYYY_MM_DD)).build();
        User user8 = new User().builder().id("id008").name("青刚影").age(20).gender("0").dob(DateUtils.parseDate("2002-10-23", YYYY_MM_DD)).build();
        User user9 = new User().builder().id("id009").name("盲僧").age(24).gender("1").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();
        User user10 = new User().builder().id("id010").name("露露").age(24).gender("0").dob(DateUtils.parseDate("1998-10-23", YYYY_MM_DD)).build();

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);

        List<User> page = users.stream().skip(0).limit(5).collect(Collectors.toList());

        System.out.println(users.size());

        System.out.println(page.size());

        page.stream().forEach(user -> System.out.println(user));

    }


    /***
     * stream流的匹配
     */
    @Test
    public void testMatch() {
        List<ClassA> aList = new ArrayList<>(Arrays.asList(
                new ClassA("1", "张三"),
                new ClassA("2", "李四"),
                new ClassA("3", "王五")
        ));
        List<ClassB> bList = new ArrayList<>(Arrays.asList(
                new ClassB("2", "李某"),
                new ClassB("3", "王某"),
                new ClassB("4", "赵某")
        ));

        //aList与bList的交集
        List<ClassA> intersectA = aList
                .stream() //获取第一个集合的Stream1
                .filter(  //取出Stream1中符合条件的元素组成新的Stream2，lambda表达式1返回值为true时为符合条件
                        a ->  //lambda表达式1，a为lambda表达式1的参数，是Stream1中的每个元素
                                bList.stream() //获取第二个集合的Stream3
                                        .map(ClassB::getId) //将第二个集合每个元素的id属性取出来，映射成新的一个Stream4
                                        .anyMatch( //返回值（boolean）：Stream4中是否至少有一个元素使lambda表达式2返回值为true
                                                id -> //lambda表达式2，id为lambda表达式2的参数，是Stream4中的每个元素
                                                        Objects.equals(a.getId(), id) //判断id的值是否相等
                                        )
                )
                .collect(Collectors.toList()); //将Stream2转换为List
        System.out.println(intersectA);


        //bList与aList的差集
        List<ClassB> differenceB = bList.stream().filter(b -> aList.stream().map(ClassA::getId).noneMatch(id -> Objects.equals(b.getId(), id))).collect(Collectors.toList());
        System.out.println(differenceB);

        //aList与bList的差集
        List<ClassA> differenceA = aList.stream().filter(a -> bList.stream().map(ClassB::getId).noneMatch(id -> Objects.equals(a.getId(), id))).collect(Collectors.toList());
        System.out.println(differenceA);
    }


    class ClassA {
        String id;
        String realName;

        public ClassA(String id, String realName) {
            this.id = id;
            this.realName = realName;
        }

        @Override
        public String toString() {
            return "ClassA{" +
                    "id='" + id + '\'' +
                    ", realName='" + realName + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public String getRealName() {
            return realName;
        }
    }

    class ClassB {
        String id;
        String nickName;

        public ClassB(String id, String nickName) {
            this.id = id;
            this.nickName = nickName;
        }

        @Override
        public String toString() {
            return "ClassB{" +
                    "id='" + id + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public String getNickName() {
            return nickName;
        }

    }


    /***
     * map集合根据value进行分组
     */
    @Test
    public void testMapGroupByValue(){
        Map<String, String> map = new HashMap<>();
        Map<String, Set<String>> groupMap = new HashMap<>();
        map.put(null, null);
        map.put(null, null);
        map.put("c", null);
        map.put("c", "3");
        map.put("e", "4");
        map.put("f", "5");
        map.put("G", "4");
        map.put("h", "5");

        //根据value分组之前
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                System.out.println(key+"->"+value);
            }
        });


        System.out.println("----------------------------------------------------");


        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String key, String value) {
                if (groupMap.containsKey(value)) {
                    groupMap.get(value).add(key);
                } else {
                    Set<String> values = new HashSet<>();
                    values.add(key);
                    groupMap.put(value, values);
                }
            }
        });

        //根据value分组之后
        groupMap.forEach(new BiConsumer<String, Set<String>>() {
            @Override
            public void accept(String value, Set<String> keys ) {
                System.out.println(value+"->"+keys);
            }
        });
    }
    
}
