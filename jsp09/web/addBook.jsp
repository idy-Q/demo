<%--
  Created by IntelliJ IDEA.
  User: TR
  Date: 2025/11/7/0007
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加图书</title>
</head>
<body>
<h1>添加图书</h1>
<form action="addBook" method="post">
    ISBN：<input type="text" name="isbn"><br>
    书名：<input type="text" name="name"><br>
    价格：<input type="text" name="price"><br>
    <input type="submit" value="提交">
</form>
<a href="findBook">返回图书列表</a>
</body>
</html>
