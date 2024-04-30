package com.j2ee.web.FolderServlet;

import com.j2ee.mapper.FileMapper;
import com.j2ee.mapper.FolderMapper;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.j2ee.pojo.qjbl.location;


@WebServlet("/enterFolderServlet")
public class EnterFolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        location+=1;
        System.out.println("全局变量："+location);
        //MySQL
        request.setCharacterEncoding("utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FolderMapper folderMapper = sqlSession.getMapper(FolderMapper.class);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);

        String id = request.getParameter("id");
        int folder_id = Integer.parseInt(id);
      //  String folder_name = folderMapper.getFolderName(folder_id);

        Cookie cookieName = new Cookie("folder_id",""+folder_id);
        response.addCookie(cookieName);


        response.sendRedirect("/selectAllFilesServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
