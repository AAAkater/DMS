package com.j2ee.mapper;
import com.j2ee.pojo.Folder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FolderMapper {

    @Select("select * from folder where user_id = #{user_id} and folder_from = #{folder_from}")
    List<Folder> selectAllfd(@Param("user_id") int user_id,@Param("folder_from") int folder_from);

    @Insert("insert into folder (folder_name,user_id,folder_from) values (#{folder_name},#{user_id},#{folder_from})")
    void insertFolder(Folder folder);

    @Delete("delete from folder where folder_id = #{folder_id}")
    void deleteFolder(int folder_id);

    @Select("select * from folder where user_id = #{user_id} order by folder_from")
    List<Folder> selectFDSortByFrom(int user_id);

    @Select("select * from folder where folder_id = #{folder_id}")
    Folder selectFolder(int folder_id);

    @Select("select folder_from from folder where folder_id = #{folder_id}")
    int selectFolderFrom(int folder_id);

}
