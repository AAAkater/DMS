package com.j2ee.web.FileServlet;

import com.j2ee.mapper.FileMapper;
import com.j2ee.pojo.Files;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.j2ee.pojo.qjbl.location;

@WebServlet("/uploadServlet")
public class UploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Files files = new Files();
        Cookie[] cookies = request.getCookies();
        int folder_id = 0;
        //数据库
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);



        //通过Cookie接受登录端的数据
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


        if(location!=0){
            for (Cookie cookie : cookies) {
                name = cookie.getName();
                if("folder_id".equals(name)){
                    id = cookie.getValue();
                    break;
                }
            }
            folder_id = Integer.parseInt(id);
        }



        int user_id = fileMapper.selectByUserId(value);
        //上传文件
        //先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的)
        if(ServletFileUpload.isMultipartContent(request)){
            //创建FiLeltemFactory工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类ServletFileUpload
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                //解析上传的数据，得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //循环判断，每一个表单项，是普通类型，还是上传的文件
                for (FileItem fileItem : list) {
                    if(fileItem.isFormField()){
                        //普通表单项
                    }else{
                        //上传的文件
                        String file_name = fileItem.getName();
                        File filePath = new File("d:/DMS/"+value+"/"+file_name);
                        if(!filePath.exists()){
                            fileItem.write(filePath);
                        }
                        String file_address = filePath.toString();


                        files.setFile_name(file_name);
                        files.setFile_address(file_address);
                        files.setUser_id(user_id);
                        files.setFile_from((location==0)?location:folder_id);


                        fileMapper.fileInsert(files);
                        sqlSession.close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/selectAllFilesServlet");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

