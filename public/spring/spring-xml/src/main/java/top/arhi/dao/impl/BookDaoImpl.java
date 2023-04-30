package top.arhi.dao.impl;

import org.springframework.stereotype.Component;
import top.arhi.dao.BookDao;

@Component("bookDao")
public class BookDaoImpl implements BookDao {
    public void add() {
        System.out.println("添加成功!");
    }
}
