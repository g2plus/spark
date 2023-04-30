package top.arhi.test.mapper.dynamic;

import org.apache.ibatis.annotations.*;
import top.arhi.domain.Person;
import top.arhi.dynamic.SqlStatement;

import java.util.List;
public interface DynamicMapper {

    @SelectProvider(type= SqlStatement.class,method="selectAll")
    List<Person> selectAll();

    @DeleteProvider(type=SqlStatement.class,method="delete")
    int delete(Integer id);

    @UpdateProvider(type=SqlStatement.class,method="update")
    int update(@Param("name")String name, @Param("id")Integer id);

    @InsertProvider(type=SqlStatement.class,method="insert")
    int insert(Person person);

}
