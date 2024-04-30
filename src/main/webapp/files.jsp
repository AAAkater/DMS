<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>文件管理系统</title>
</head>
<body>

<h1>${user.user_name},欢迎您！</h1>



<form action="uploadServlet" method="post" enctype="multipart/form-data">
    文件:<input type="file" name="file" ><br>
    <input type="submit" value="上传" >
</form>

<form action="passwordChangeServlet" method="post" id="change">
    <p><input id="password" name="password" type="text">
        <button type="submit">修改密码</button></p>
</form>


    <a href="login.jsp">退出登录</a><br><br><br>


<form action="createFolderServlet" method="post">
    <p><input id="folder_name" name="folder_name" type="text">
        <button type="submit">创建文件夹</button></p>
</form>


<a href="returnServlet">返回根目录</a>

<br>
<table border="1" cellspacing="0" width="800">
    <tr>
        <th>文件夹名</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${folders}" var="folders" varStatus="status">
        <tr align="center">
            <td>${folders.folder_name}</td>>
            <td>
                <a href="./enterFolderServlet?id=${folders.folder_id}">进入</a>
                <a href="./deleteFolderServlet?id=${folders.folder_id}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>







<table border="1" cellspacing="0" width="800">
    <tr>
        <th>文件名</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${files}" var="files" varStatus="status">
        <tr align="center">
            <td>${files.file_name}</td>>
            <td>
                <a href="./downloadServlet?id=${files.file_id}">下载</a>
                <a href="./deleteFileIdServlet?id=${files.file_id}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>



</body>

</html>