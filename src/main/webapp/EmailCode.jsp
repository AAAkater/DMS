<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>邮箱验证界面</title>
    <style type="text/css">
        .table tr {
            border-collapse: collapse;
        }
    </style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.0.js"></script>
    <script type="text/javascript">
        var emailRegExp=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;//验证邮箱的正则表达式
        $(function(){
            $("#btn").click(function(){
                if(emailRegExp.test($(".email").val())){
                    $.ajax({
                        type:"POST",
                        url :"SendEmailServlet?random"+Math.random(),
                        data:{
                            email:$(".email").val(),
                        },
                        success:function(data){
                            alert("发送成功");
                        },
                    })
                }else{
                    alert("请填写正确邮箱");
                    $("#notice").html("请填写正确邮箱");
                    setTimeout(function(){
                        $("#notice").hide();
                    },1000);
                }
            });
            //  判断用户是否可以注册
            $("#submit").click(function(){
                if($(".email").val()){
                    $("#RegistForm").submit();
                }else{   //  如果没有值
                    $("#notice").html("请完整信息");
                    setTimeout(function(){
                        $("#notice").hide();
                    },1000);
                }
            });
        });
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/emailCodeServlet" id="RegistForm"method="post">
        <tr>
            <td>邮箱:</td>
            <td><input type="text" name="email" class="email"></td>
            <td>
                <input type="button" class="btn" id="btn" value="发送邮箱验证码"/><br/>
            </td>
        </tr>
        <tr>
            <td>验证码:</td>
            <td><input type="text" name="code" class="code"></td>
        </tr><br>
        <tr>
            <td colspan="2"><input type="submit" id="submit" value="注册"></td>
        </tr>
</form>
</body>
</html>