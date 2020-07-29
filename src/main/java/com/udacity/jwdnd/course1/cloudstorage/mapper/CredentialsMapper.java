package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("SELECT * FROM credentials WHERE userid = #{userid} ")
    @Results(value = {
            @Result(property = "credentialId", column = "credentialid"),
            @Result(property = "url", column = "url"),
            @Result(property = "userName", column = "username"),
            @Result(property = "key", column = "key"),
            @Result(property = "password", column = "password"),
            @Result(property = "userId", column = "userid")
    })
    List<Credentials> loadAllCredentials(Integer userId);

    @Delete("DELETE credentials WHERE credentialid = #{credentialId}")
    int deleteCredentials(Integer credentialId);


    @Insert("INSERT INTO CREDENTIALS (url, username, key, password,userid) VALUES(#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int saveCredentials(Credentials credentials);

    @Select("SELECT * FROM credentials WHERE credentialid = #{credentialId} ")
    @Results(value = {
            @Result(property = "credentialId", column = "credentialid"),
            @Result(property = "url", column = "url"),
            @Result(property = "userName", column = "username"),
            @Result(property = "key", column = "key"),
            @Result(property = "password", column = "password"),
            @Result(property = "userId", column = "userid")
    })
    Credentials loadCredentialsById(Integer credentialId);

    @Update("UPDATE CREDENTIALS SET url = #{url},username = #{userName} ,key = #{key} , password = #{password} where credentialid = #{credentialId}  and userid= #{userId} ")
    int updateCredentials(Credentials credentials);

}
