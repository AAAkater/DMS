package com.j2ee.web.FileServlet;

import com.j2ee.mapper.FileMapper;
import com.j2ee.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //MySQL
        request.setCharacterEncoding("utf-8");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        FileMapper fileMapper = sqlSession.getMapper(FileMapper.class);
        String id = request.getParameter("id");
        int fileId = Integer.parseInt(id);
        String fileName = fileMapper.selectFileName(fileId);
        String path = fileMapper.selectFileAddress(fileId);
        //读取要下载的文件内容（通过ServletContext对象可以读取)
        ServletContext servletcontext = getServletContext();
        //获取要下载的文件类型
        String mimeType = servletcontext.getMimeType(path);
        System.out.println("下载的文件类型:" +mimeType);
        //在回传前，通过响应头告诉客户端返回的数据类型
        response.setContentType(mimeType);
        //告诉客户端收到的数据是用于下载使用(还是使用响应头)
        response.setHeader("Content-Disposition","attachment;filename="+fileName);
        InputStream in = new FileInputStream(path);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[10240];
        int len;
        //循环取出流中的数据
        while((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
