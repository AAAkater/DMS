package com.j2ee.web.FolderServlet;

import com.j2ee.mapper.FileMapper;
import com.j2ee.mapper.FolderMapper;
import com.j2ee.pojo.Files;
import com.j2ee.pojo.Folder;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteFolderServlet")
public class DeleteFolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FolderMapper folderMapper = sqlSession.getMapper(FolderMapper.class);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);


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


        String id = request.getParameter("id");
        int folder_id = Integer.parseInt(id);
        folderMapper.deleteFolder(folder_id);
        //删文件夹

        List<Folder> folderToDelete = folderMapper.selectFDSortByFrom(user_id);
        for (Folder folder : folderToDelete) {
            if(folderMapper.selectFolder(folder.getFolder_from()) == null &&
                    folder.getFolder_from()!=0){
                folderMapper.deleteFolder(folder.getFolder_id());
            }
        }
         //删文件
        List<Files> filesToDelete = fileMapper.selectAllFilesToDelete(user_id);
        for (Files file : filesToDelete) {
            File fileReality = new File(file.getFile_address());
            //判断是否只有一个文件，若否，则不删本地只删数据库里的。（重名问题）
            if (fileMapper.isUnique(user_id,file.getFile_name()) == 1){
                fileReality.delete();
            }
            fileMapper.deleteByFileId(file.getFile_id());
        }

        response.sendRedirect("/selectAllFilesServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
