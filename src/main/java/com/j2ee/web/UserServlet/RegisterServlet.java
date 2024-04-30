package com.j2ee.web.UserServlet;

import com.j2ee.mapper.UserMapper;
import com.j2ee.pojo.User;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受用户数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        //封装User对象
        User user = new User();
        user.setuser_name(username);
        user.setuser_password(password);

        //2.1获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.2获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.3获取Mapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        

        //判断操作
        User u = userMapper.selectByUsername(username);
        //判断用户对象是否为null
        if(u==null){
            //用户名不存在，可以添加
            userMapper.add(user);
            //提交事务
            sqlSession.commit();
            //释放资源
            sqlSession.close();
            //跳转到邮箱验证界面
            request.getRequestDispatcher("/EmailCode.jsp").forward(request,response);
        }else{
            //用户命已存在，重新输入
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
