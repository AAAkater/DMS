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
        //�����û�����
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        //��װUser����
        User user = new User();
        user.setuser_name(username);
        user.setuser_password(password);

        //2.1��ȡSqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        //2.2��ȡSqlSession����
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.3��ȡMapper
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        

        //�жϲ���
        User u = userMapper.selectByUsername(username);
        //�ж��û������Ƿ�Ϊnull
        if(u==null){
            //�û��������ڣ��������
            userMapper.add(user);
            //�ύ����
            sqlSession.commit();
            //�ͷ���Դ
            sqlSession.close();
            //��ת��������֤����
            request.getRequestDispatcher("/EmailCode.jsp").forward(request,response);
        }else{
            //�û����Ѵ��ڣ���������
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
