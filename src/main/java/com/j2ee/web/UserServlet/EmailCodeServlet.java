package com.j2ee.web.UserServlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/emailCodeServlet")
public class EmailCodeServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sessionCode = (String) req.getSession().getAttribute("code");
        //  ��ȡsession�е���֤��
        if(sessionCode != null) {
            //  ��ȡҳ���ύ����֤��
            String inputCode = req.getParameter("code");
            System.out.println("ҳ���ύ����֤��:" + inputCode);
            if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
                //  ��֤�ɹ�����ת�ɹ�ҳ��
                req.getRequestDispatcher("/login.html").forward(req, resp);
            }else {
                //  ��֤ʧ��
                req.getRequestDispatcher("EmailCode.jsp").forward(req, resp);
            }
        }else {
            //  ��֤ʧ��
            req.getRequestDispatcher("EmailCode.jsp").forward(req, resp);
        }
        //  �Ƴ�session�е���֤��
        req.removeAttribute("code");
    }
}