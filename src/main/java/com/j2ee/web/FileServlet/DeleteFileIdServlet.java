package com.j2ee.web.FileServlet;

import com.j2ee.mapper.FileMapper;
import com.j2ee.pojo.Files;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/deleteFileIdServlet")
public class DeleteFileIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MySQL
        request.setCharacterEncoding("utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
        //获取file_id,进行删除操作
        String id = request.getParameter("id");
        System.out.println(id);
        int file_id = Integer.parseInt(id);


        //通过Cookie接受登录端的数据
        String name;
        String value = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            name = cookie.getName();
            if("username".equals(name)){
                value = cookie.getValue();
                break;
            }
        }
        int user_id = fileMapper.selectByUserId(value);


        //先删除文件再删除MySQL中数据
        String path = fileMapper.selectFileAddress(file_id);
        Files files = fileMapper.selectFile(file_id);
        File file = new File(path);
        //判断是否只有一个文件，若否，则不删本地只删数据库里的。（重名问题）
        if (fileMapper.isUnique(user_id,files.getFile_name()) == 1){
            file.delete();
        }
        fileMapper.deleteByFileId(file_id);
        response.sendRedirect("/selectAllFilesServlet");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
