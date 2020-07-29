package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

   @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
   @Options(useGeneratedKeys = true, keyProperty = "noteId")
   int createNote(Note note);


    @Select("SELECT noteId, noteTitle, noteDescription, userId FROM NOTES WHERE userid = #{userId}")
    @Results(value = {
            @Result(property = "noteId", column = "noteid"),
            @Result(property = "noteTitle", column = "notetitle"),
            @Result(property = "noteDescription", column = "notedescription"),
            @Result(property = "userId", column = "userid")
    })
    List<Note> loadAllNotes(Integer userId);


    @Select("SELECT * FROM NOTES WHERE userid = #{userId} and notetitle= #{noteTitle}")
    @Results(value = {
            @Result(property = "noteId", column = "noteid"),
            @Result(property = "noteTitle", column = "notetitle"),
            @Result(property = "noteDescription", column = "notedescription"),
            @Result(property = "userId", column = "userid")
    })
    Note loadNoteByName(Integer userId, String noteTitle);

    @Select("SELECT * FROM NOTES WHERE noteid = #{noteId} ")
    @Results(value = {
            @Result(property = "noteId", column = "noteid"),
            @Result(property = "noteTitle", column = "notetitle"),
            @Result(property = "noteDescription", column = "notedescription"),
            @Result(property = "userId", column = "userid")
    })
    Note loadNoteById(Integer noteId);

    @Delete("DELETE NOTES WHERE userid = #{userId} and notetitle = #{noteTitle}")
    int deleteNote(Integer userId , String noteTitle);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle} ,notedescription = #{noteDescription} WHERE noteid = #{noteId}")
    int updateNote(Note note);

}