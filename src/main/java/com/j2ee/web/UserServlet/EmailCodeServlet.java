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
        //  获取session中的验证码
        if(sessionCode != null) {
            //  获取页面提交的验证码
            String inputCode = req.getParameter("code");
            System.out.println("页面提交的验证码:" + inputCode);
            if (sessionCode.toLowerCase().equals(inputCode.toLowerCase())) {
                //  验证成功，跳转成功页面
                req.getRequestDispatcher("/login.html").forward(req, resp);
            }else {
                //  验证失败
                req.getRequestDispatcher("EmailCode.jsp").forward(req, resp);
            }
        }else {
            //  验证失败
            req.getRequestDispatcher("EmailCode.jsp").forward(req, resp);
        }
        //  移除session中的验证码
        req.removeAttribute("code");
    }
}