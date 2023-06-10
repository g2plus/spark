package com.cpc.multidbtx.test.mybatis;

import com.cpc.multidbtx.Application;
import com.cpc.multidbtx.config.DynamicDataSource;
import com.cpc.multidbtx.domain.Account;
import com.cpc.multidbtx.domain.Book;
import com.cpc.multidbtx.domain.Good;
import com.cpc.multidbtx.enums.StateEnum;
import com.cpc.multidbtx.mapper.BookMapper;
import com.cpc.multidbtx.service.AccountService;
import com.cpc.multidbtx.service.BookService;
import com.cpc.multidbtx.service.GoodService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cpc.multidbtx.util.IdUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
public class FunctionTest {

    @Resource
    private BookService bookService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GoodService goodService;


    @Autowired
    private BookMapper bookMapper;

    /**
     * 测试多线程事务.
     *
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        long begin = System.currentTimeMillis();
        int size = 100;
        List<Book> bookList = new ArrayList<Book>(size);
        for (long i = 0; i < size; i++) {
            Book book = new Book();
            book.setBookId(i + 1);
            book.setBookName("book" + i + 1);
            book.setBookAuthorName("bookauthor" + i + 1);
            bookList.add(book);
        }
        try {
            DynamicDataSource.setDataSource("default");
            bookMapper.saveBatch(bookList);
            long end = System.currentTimeMillis();
            System.out.println("添加成功,耗时" + (end - begin) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试mp的查询 queryWrapper的使用
     */
    @Test
    public void test2() {
        Book book = bookService.selectOne(1L);
        System.out.println(book);
    }


    /**
     * 根据主键进行查询
     */
    @Test
    public void test3() {
        Book book = bookService.selectByPrimaryKey(1L);
        System.out.println(book);
    }


    /**
     * 测试根据条件 LambaQueryWrapper避免字段书写错误
     */
    @Test
    public void test4() {
        Book book = bookService.select(1L);
        System.out.println(book);
    }


    /***
     * 测试mp的 selectPage方法
     */
    @Test
    public void test5() {
        IPage<Book> bookIPage = bookService.selectPage(1L, 10L);
        System.out.println(bookIPage.getRecords());
    }


    /**
     * 测试mp的枚举设置数据库值
     * 实体类继承BaseEntity
     * 字段的自动填充功能
     */
    @Test
    public void test6() {
        Account account = new Account();
        account.setAccountId(IdUtils.getStrId());
        account.setAccountName("test");
        account.setState(StateEnum.ENABLE);
        accountService.insert(account);
    }


    /**
     * 此处没有直接使用mp的乐观锁，根据原理手动实现
     * 测试TablePrefix是否生效,请查看MybatisPlusConfig的GlobalConfig
     */
    @Test
    public void test7() {
        Good good = new Good();
        good.setGoodId(IdUtils.getStrId());
        good.setGoodName("test");
        good.setTotal(1000L);
        goodService.insert(good);
    }

    /**
     * 多线程模拟扣减库存的操作
     */
    @Test
    public void test8() throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                sell1();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                sell1();
            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                sell1();
            }
        });
        threadA.setName("路人甲");
        threadB.setName("路人乙");
        threadC.setName("路人丙");
        threadA.start();
        threadB.start();
        Thread.sleep(3000);
        threadC.start();

    }

    public void sell1() {
        String goodId = "1586620453682253824";
        Map<String, Object> retMap = goodService.sellGood1(goodId);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(Thread.currentThread().getName()+"---"+retMap);
        System.out.println("---------------------------------------------------------------------------");
    }

    /**
     * 使用mp的乐观锁
     */
    @Test
    public void test9() throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                sell2();
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                sell2();
            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                sell2();
            }
        });
        threadA.setName("路人甲");
        threadB.setName("路人乙");
        threadC.setName("路人丙");
        threadA.start();
        threadB.start();
        threadB.sleep(3000);
        threadC.start();
    }


    public void sell2() {
        String goodId = "1586620453682253824";
        Map<String, Object> retMap = goodService.sellGood2(goodId);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(Thread.currentThread().getName() + "---" + retMap);
        System.out.println("---------------------------------------------------------------------------");
    }


    public void sell3() throws InterruptedException {
        String goodId = "1586620453682253824";
        Map<String, Object> retMap = goodService.sellGood3(goodId);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println(Thread.currentThread().getName() + "---" + retMap);
        System.out.println("---------------------------------------------------------------------------");
    }


    /**
     * 单线程测试乐观锁插件是否生效
     * 在查询到数据库数据后,直接修改数据库的版本.然后测试是否发生修改可以验证是否生效
     */
    @Test
    public void test10() {
        sell2();
    }

    /**
     * mp的逻辑上删除
     */
    @Test
    public void test11(){
        Book book = new Book();
        book.setBookId(null);
        book.setBookName("你我他");
        book.setBookAuthorName("你我他");
        bookService.insert(book);
    }

    /**
     * 测试mp的逻辑删除的是否有效
     */
    @Test
    public void test12(){
        List<Book> books = bookService.selectList();
        List<String> collect1 = books.stream().map(Book::getEnabled).collect(Collectors.toList());
        System.out.println("------------------------------");
        System.out.println(collect1.size());
        List<String> collect2 = collect1.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return "1".equals(s);
            }
        }).collect(Collectors.toList());
        System.out.println(collect2.size());
        System.out.println("------------------------------");
        System.out.println(books.size());
        System.out.println("------------------------------");
        System.out.println(books);
    }


    /***
     *涉及到多条数据修改的时候，请使用xml，编辑批量更新，速度快的一逼
     */

    /**
     * 测试批量更新
     */
    @Test
    public void test13(){
        Integer size=10000;
        List<Book> books = new ArrayList<Book>();
        for (long i = 0; i < size; i++) {
            Book book = new Book();
            book.setBookId(i + 1);
            book.setBookName("book-batch-a-" + i + 1);
            book.setBookAuthorName("bookauthor" + i + 1);
            book.setEnabled("1");
            books.add(book);
        }
        long begin=System.currentTimeMillis();
        bookService.updateBatchA(books);
        long end = System.currentTimeMillis();
        System.out.println("更新"+size+"条数据用时"+(end-begin)+"ms");
        /**
         *1899,2047,1953
         */

    }


    /***
     * 测试批量更新
     */
    @Test
    public void test13A(){
        Integer size=10000;
        List<Book> books = new ArrayList<Book>();
        for (long i = 0; i < size; i++) {
            Book book = new Book();
            book.setBookId(i + 1);
            book.setBookName("book-batch-b-" + i + 1);
            book.setBookAuthorName("bookauthor" + i + 1);
            book.setEnabled("1");
            books.add(book);
        }
        long begin=System.currentTimeMillis();
        int updated = bookService.updateBatchB(books);
        long end = System.currentTimeMillis();
        System.out.println("更新"+size+"条数据用时"+(end-begin)+"ms");
        System.out.println(updated);
        /**
         *14379,13866,13998
         */
    }

    /**
     * 使用遍历方式，每次更新一条数据,使用xml
     */
    @Test
    public void test14(){
        Integer size=10000;
        long begin=System.currentTimeMillis();
        DynamicDataSource.setDataSource("default");
        for (long i = 0; i < size; i++) {
            Book book = new Book();
            book.setBookId(i + 1);
            book.setBookName("book-single-xml-" + i + 1);
            book.setBookAuthorName("bookauthor" + i + 1);
            book.setEnabled("1");
            bookService.updateA(book);
        }
        DynamicDataSource.clearDataSource();
        long end = System.currentTimeMillis();
        System.out.println("更新"+size+"条数据用时"+(end-begin)+"ms");
        /**
         *64983,65685,54768
         */
    }


    /**
     * 使用mp的更新  每次更新一条数据
     */
    @Test
    public void test16(){
        Integer size=10000;
        long begin=System.currentTimeMillis();
        DynamicDataSource.setDataSource("default");
        for (long i = 0; i < size; i++) {
            Book book = new Book();
            book.setBookId(i + 1);
            book.setBookName("book-single-logic-" + i + 1);
            book.setBookAuthorName("bookauthor" + i + 1);
            //!!!注意
            //使用mp做更新,设置逻辑删除字段的值为删除的value是无效的
            book.setEnabled("3");
            bookService.updateB(book);
        }
        DynamicDataSource.clearDataSource();
        long end = System.currentTimeMillis();
        System.out.println("更新" + size + "条数据用时" + (end - begin) + "ms");
        /**
         *66766,51613
         */
    }


    /**
     * 锁与事务
     */
    @Test
    public void test17() {
        //for (int i = 0; i < 500; i++) {
            Thread threadA = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        sell3();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threadA.start();
        //}
    }



}
