package com.j2ee.web.FileServlet;

import com.j2ee.mapper.FileMapper;
import com.j2ee.mapper.FolderMapper;
import com.j2ee.pojo.Files;
import com.j2ee.pojo.Folder;
import com.j2ee.service.FilesService;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

import static com.j2ee.pojo.qjbl.location;

@WebServlet("/selectAllFilesServlet")
public class SelectAllFilesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Files> files = null;
        List<Folder> folders = null;
        int folder_id = 0;
        //数据库
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
        FolderMapper folderMapper = sqlSession.getMapper(FolderMapper.class);

//

        //
        String name;
        String value = null;
        String id = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            name = cookie.getName();
            if("username".equals(name)){
                value = cookie.getValue();
                break;
            }
        }
        for (Cookie cookie : cookies) {
            name = cookie.getName();
            if("folder_id".equals(name)){
                id = cookie.getValue();
                break;
            }
        }


        if(id!=null){
            folder_id = Integer.parseInt(id);
        }

        int user_id = fileMapper.selectByUserId(value);


        FilesService service = new FilesService();

        files = service.selectAllFiles(user_id,(location==0)?location:folder_id);
        folders = folderMapper.selectAllfd(user_id,(location==0)?location:folder_id);
        //存入request域中
        request.setAttribute("files",files);
        request.setAttribute("folders",folders);
        //转发到files.jsp
        request.getRequestDispatcher("/files.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
