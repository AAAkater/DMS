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
        //���ݿ�
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);



        //ͨ��Cookie���ܵ�¼�˵�����
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
        //�ϴ��ļ�
        //���ж��ϴ��������Ƿ������ݣ�ֻ���Ƕ�ε����ݣ������ļ��ϴ���)
        if(ServletFileUpload.isMultipartContent(request)){
            //����FiLeltemFactory����ʵ����
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //�������ڽ����ϴ����ݵĹ�����ServletFileUpload
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            try {
                //�����ϴ������ݣ��õ�ÿһ������FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //ѭ���жϣ�ÿһ���������ͨ���ͣ������ϴ����ļ�
                for (FileItem fileItem : list) {
                    if(fileItem.isFormField()){
                        //��ͨ����
                    }else{
                        //�ϴ����ļ�
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

