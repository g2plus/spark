package top.arhi.mapper;

import top.arhi.domain.Zip;

public interface ZipMapper {
    //TODO 使用java原生态进行文件的解压与压缩

    String selectByTargetName();
    Integer add(Zip zip);
}
