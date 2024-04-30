/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-11-29 11:46:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class EmailCode_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\r\n");
      out.write("    <title>邮箱验证界面</title>\r\n");
      out.write("    <style type=\"text/css\">\r\n");
      out.write("        .table tr {\r\n");
      out.write("            border-collapse: collapse;\r\n");
      out.write("        }\r\n");
      out.write("    </style>\r\n");
      out.write("    <script type=\"text/javascript\" src=\"http://code.jquery.com/jquery-1.10.0.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        var emailRegExp=/^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$/;//验证邮箱的正则表达式\r\n");
      out.write("        $(function(){\r\n");
      out.write("            $(\"#btn\").click(function(){\r\n");
      out.write("                if(emailRegExp.test($(\".email\").val())){\r\n");
      out.write("                    $.ajax({\r\n");
      out.write("                        type:\"POST\",\r\n");
      out.write("                        url :\"SendEmailServlet?random\"+Math.random(),\r\n");
      out.write("                        data:{\r\n");
      out.write("                            email:$(\".email\").val(),\r\n");
      out.write("                        },\r\n");
      out.write("                        success:function(data){\r\n");
      out.write("                            alert(\"发送成功\");\r\n");
      out.write("                        },\r\n");
      out.write("                    })\r\n");
      out.write("                }else{\r\n");
      out.write("                    alert(\"请填写正确邮箱\");\r\n");
      out.write("                    $(\"#notice\").html(\"请填写正确邮箱\");\r\n");
      out.write("                    setTimeout(function(){\r\n");
      out.write("                        $(\"#notice\").hide();\r\n");
      out.write("                    },1000);\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("            //  判断用户是否可以注册\r\n");
      out.write("            $(\"#submit\").click(function(){\r\n");
      out.write("                if($(\".email\").val()){\r\n");
      out.write("                    $(\"#RegistForm\").submit();\r\n");
      out.write("                }else{   //  如果没有值\r\n");
      out.write("                    $(\"#notice\").html(\"请完整信息\");\r\n");
      out.write("                    setTimeout(function(){\r\n");
      out.write("                        $(\"#notice\").hide();\r\n");
      out.write("                    },1000);\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("        });\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/emailCodeServlet\" id=\"RegistForm\"method=\"post\">\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>邮箱:</td>\r\n");
      out.write("            <td><input type=\"text\" name=\"email\" class=\"email\"></td>\r\n");
      out.write("            <td>\r\n");
      out.write("                <input type=\"button\" class=\"btn\" id=\"btn\" value=\"发送邮箱验证码\"/><br/>\r\n");
      out.write("            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td>验证码:</td>\r\n");
      out.write("            <td><input type=\"text\" name=\"code\" class=\"code\"></td>\r\n");
      out.write("        </tr><br>\r\n");
      out.write("        <tr>\r\n");
      out.write("            <td colspan=\"2\"><input type=\"submit\" id=\"submit\" value=\"注册\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
