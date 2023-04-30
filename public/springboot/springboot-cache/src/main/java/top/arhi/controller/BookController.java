package top.arhi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.arhi.domain.Book;
import top.arhi.domain.User;
import top.arhi.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;



    @GetMapping("/{id}")
    public Book findByIdWithOutCache(@PathVariable("id") Long id) {
        return bookService.findByIdWithOutCache(id);
    }


    /*支持缓存的接口*/

    @GetMapping("/hashMap/{id}")
    public Book findById_hashMap(@PathVariable("id") Long id){
        return bookService.findById_hashMap(id);
    }


    @GetMapping("/ehcache/{id}")
    public Book findById_ecache(@PathVariable("id") Long id){
        return bookService.findById_ehcache(id);
    }

    @GetMapping("/redis/{id}")
    public Book findById_redis(@PathVariable("id") Long id){
        return bookService.findById_redis(id);
    }

    @GetMapping("/memcache/{id}")
    public Book findById_memcache(@PathVariable("id") Long id){
        return bookService.findById_memcache(id);
    }




}
