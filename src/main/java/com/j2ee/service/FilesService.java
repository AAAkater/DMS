package com.j2ee.service;

import com.j2ee.mapper.FileMapper;
import com.j2ee.pojo.Files;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
public class FilesService {
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();


    public List<Files> selectAllFiles(int user_id,int folder_from){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
        List<Files> files = fileMapper.selectAllFiles(user_id,folder_from);
        sqlSession.close();
        return files;
    }

}
