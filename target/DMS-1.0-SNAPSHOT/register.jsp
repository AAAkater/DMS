<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>



<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.html">登录</a>
    </div>
    <form action="registerServlet" method="post" onSubmit="return beforeSubmit(this);">
        <table>
            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg" style="display: none">用户名已存在</span>
                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>
</div>
<script type="text/javascript">
    function beforeSubmit(form){
        if(form.username.value==''){
            alert('用户名不能为空！');
            form.username.focus();
            return false;
        }
        if(form.password.value==''){
            alert('密码不能为空！');
            form.password.focus();
            return false;
        }
        return true;
    }
</script>











<script>
    //异步判断用户名是否存在
    document.getElementById("username").onblur = function () {
        var username = this.value;
        var xhttp;
        if (window.XMLHttpRequest) {
            xhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xhttp.open("GET", "http://localhost/selectUserServlet?username="+username);
        xhttp.send();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                if(this.responseText=="true"){
                    document.getElementById("username_err").style.display = '';
                }else{
                    document.getElementById("username_err").style.display = 'none';
                }
            }
        }
    }

</script>
</body>
</html>