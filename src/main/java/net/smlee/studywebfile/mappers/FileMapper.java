package net.smlee.studywebfile.mappers;

import net.smlee.studywebfile.entities.FileEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    int insertFile(FileEntity file);
}
