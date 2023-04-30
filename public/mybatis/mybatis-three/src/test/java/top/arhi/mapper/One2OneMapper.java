package top.arhi.mapper;

import top.arhi.bean.IDCard;

import java.util.List;

public interface One2OneMapper {
    /**
     * 查询全部
     * @return
     */
   List<IDCard> selectAll();
}
