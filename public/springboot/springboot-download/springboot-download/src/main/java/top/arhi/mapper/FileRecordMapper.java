package top.arhi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.arhi.entity.FileRecord;

@Mapper
public interface FileRecordMapper {
    void insertFileRecord(FileRecord fileRecord);
    FileRecord findByFileId(@Param("fileId") String fileId);
}
