package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface FileMapper {
    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int saveFile(File file);

    @Select("SELECT fileId, filename, contenttype, filesize, userid, filedata FROM FILES WHERE userId = #{userId}")
    @Results(value = {
            @Result(property = "fileId", column = "fileId"),
            @Result(property = "fileName", column = "filename"),
            @Result(property = "contentType", column = "CONTENTTYPE"),
            @Result(property = "fileSize", column = "filesize"),
            @Result(property = "userId", column = "userid"),
            @Result(property = "fileData", column = "filedata")
    })
    List<File> loadFilesForUser(Integer userId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId} and fileName = #{fileName}")
    File getFileForUserByName(Integer userId , String fileName);

    @Delete("DELETE FILES WHERE userId = #{userId} and fileName = #{fileName}")
    int deleteFile(Integer userId , String fileName);

}
