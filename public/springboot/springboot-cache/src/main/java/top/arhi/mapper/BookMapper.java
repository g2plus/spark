package top.arhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.arhi.domain.Book;


@Mapper
public interface BookMapper extends MyBaseMapper<Book> {

   Book findById(Long id);
}
