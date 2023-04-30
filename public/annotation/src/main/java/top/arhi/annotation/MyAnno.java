package top.arhi.annotation;

public @interface MyAnno {

    //java中八大基本数据类型
//    byte test1();
//    short test2();
//    int test3();
//    long test4();
//
//    float test5();
//    float test6();
//
//    boolean test7();
//    char test8();

    //注解的属性类型不支持void
    //void test9();

    //八种基本类型的包装类型不可用
    //Byte test10();

//    String test11();
//
//    Human test12();



//    byte[] test13();
//    short[] test14();
//    int[] test15();
//    long[] test16();
//
//    float[] test17();
//    double[] test18();
//
//    boolean[] test19();
//    char[] test20();
//
//    String[] test21();
//    Human[] test22();
//
//    Anno test23();
//    Anno[] test24();

    String value() default "海王";

}
