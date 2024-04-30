package com.j2ee.web.UserServlet;

import com.j2ee.mapper.UserMapper;
import com.j2ee.pojo.User;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

import static com.j2ee.pojo.qjbl.location;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        location=0;
        //1.�����û���������
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2������MyBatis��ɲ�ѯ
        //2.1��ȡSqlSessionFactory
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectRegister(username,password);
        sqlSession.close();


        //3������user�Ƿ�Ϊnull
        if(user != null){
            //��½�ɹ�
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            File file = new File("d:/DMS/"+username);
            if(!file.exists()){
                file.mkdirs();
            }
            Cookie cookieName = new Cookie("username", username);
            resp.addCookie(cookieName);
            resp.sendRedirect("/selectAllFilesServlet");
        }else{
            //��½ʧ��,�洢������Ϣ��request
            req.setAttribute("login_err","�û������������");
            //��ת��login.jsp
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
