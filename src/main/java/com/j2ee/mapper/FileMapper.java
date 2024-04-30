package com.j2ee.mapper;

import com.j2ee.pojo.Files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FileMapper {

    void fileInsert(Files files);

    @Delete("delete from file where file_id = #{file_id}")
    void deleteByFileId(int file_id);

    @Select("select user_id from user where user_name = #{name}")
    int selectByUserId(String name);

    @Select("select * from file where user_id  = #{user_id} and file_from = #{file_from}")
    List<Files> selectAllFiles(@Param("user_id")int user_id,@Param("file_from") int file_from);

    @Select("select file_address from file where file_id = #{file_id}")
    String selectFileAddress(int file_id);

    @Select("select file_name from file where file_id = #{file_id}")
    String selectFileName(int file_id);

    @Update("update user set user_password = #{user_password} where user_id = #{user_id}")
    void updatePassword(@Param("user_password") String user_password,@Param("user_id") int user_id);

    @Select("select * from file where user_id=#{user_id} and file_from!=0 " +
            "and file_from not in (select folder_id from folder)")
    List<Files> selectAllFilesToDelete(int user_id);

    @Select("select count(file_name) from file where user_id = #{user_id} and file_name = #{file_name}")
    int isUnique(@Param("user_id")int user_id,@Param("file_name") String file_name);

    @Select("select * from file where file_id=#{file_id}")
    Files selectFile(int file_id);

}
