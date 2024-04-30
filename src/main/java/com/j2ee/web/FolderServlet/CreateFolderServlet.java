package com.j2ee.web.FolderServlet;

import com.j2ee.mapper.FileMapper;
import com.j2ee.mapper.FolderMapper;
import com.j2ee.pojo.Folder;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static com.j2ee.pojo.qjbl.location;

@WebServlet("/createFolderServlet")
public class CreateFolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Folder folder = new Folder();
        int folder_id = 0;
        Cookie[] cookies = request.getCookies();
        //MySQL
        request.setCharacterEncoding("utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FolderMapper folderMapper = sqlSession.getMapper(FolderMapper.class);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);



        String name;
        String value = null;
        String id = null;
        for (Cookie cookie : cookies) {
            name = cookie.getName();
            if("username".equals(name)){
                value = cookie.getValue();
                break;
            }
        }
        if(location!=0) {
            for (Cookie cookie : cookies) {
                name = cookie.getName();
                if ("folder_id".equals(name)) {
                    id = cookie.getValue();
                    break;
                }
            }
            folder_id = Integer.parseInt(id);
        }




        String folder_name = request.getParameter("folder_name");
        int user_id = fileMapper.selectByUserId(value);






        folder.setFolder_name(folder_name);
        folder.setFolder_from((location==0)?location:folder_id);
        folder.setUser_id(user_id);









        folderMapper.insertFolder(folder);





        response.sendRedirect("/selectAllFilesServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
