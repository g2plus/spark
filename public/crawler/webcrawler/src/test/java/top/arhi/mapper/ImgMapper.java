package top.arhi.mapper;

import org.apache.ibatis.annotations.Param;

public interface ImgMapper {
    public abstract void add(@Param("dbsource")String dbsource, @Param("website") String website, @Param("url")String url);
}
