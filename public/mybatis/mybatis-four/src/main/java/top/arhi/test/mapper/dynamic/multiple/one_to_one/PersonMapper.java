package top.arhi.test.mapper.dynamic.multiple.one_to_one;


import top.arhi.domain.Person;
import org.apache.ibatis.annotations.Select;


public interface PersonMapper {
    @Select("SELECT * FROM person where id=#{id}")
    Person selectById(Integer id);
}
