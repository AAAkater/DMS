package com.j2ee.mapper;

import com.j2ee.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from user where user_name = #{user_name} and user_password = #{password}")
    User selectRegister(@Param("user_name") String user_name,@Param("password") String password);

    //�����û�����ѯ�û�����
    @Select("select *from user where user_name = #{user_name}")
    User selectByUsername(String user_name);

    //���ע��
    @Insert("insert into user values (null,#{user_name},#{user_password})")
    void add(User user);

    @Select("select user_name from user where user_name = #{user_name}")
    String getUsername(String user_name);




}
