package com.j2ee.web.UserServlet;

import com.j2ee.mapper.FileMapper;
import com.j2ee.mapper.UserMapper;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/selectUserServlet")
public class SelectUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MySQL
        request.setCharacterEncoding("utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //1. 接收用户名
        String username = request.getParameter("username");

        //2. 调用service查询User对象

        boolean flag = username.equals(userMapper.getUsername(username));

        //3. 响应标记
        response.getWriter().write("" + flag);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
