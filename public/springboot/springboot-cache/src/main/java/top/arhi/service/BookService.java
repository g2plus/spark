package top.arhi.service;

import top.arhi.domain.Book;

public interface BookService {

    Book findByIdWithOutCache(Long id);

    Book findById_hashMap(Long id);

    Book findById_ehcache(Long id);

    Book findById_redis(Long id);

    Book findById_memcache(Long id);
}
