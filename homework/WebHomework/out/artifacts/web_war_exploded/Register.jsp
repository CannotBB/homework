<%--
  Created by IntelliJ IDEA.
  User: 云继
  Date: 2019/1/7
  Time: 22:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center">注册</h1>
<form action="/servlet1" method="post">
    用户名: <input type="text" name="username"><br>
    密码:<input type="password" name="password"><br>
    性别:<input type="text" name="sex"><br>
    年龄:<input type="text" name="age"><br>
    电话号码:<input type="text" name="telephone"><br>
    <input type="submit" value="注册">
</form>

</body>
</html>
