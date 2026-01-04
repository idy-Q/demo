<%--
  Created by IntelliJ IDEA.
  User: TR
  Date: 2025/11/7/0007
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>修改图书</title>
</head>
<body>
<h1>修改图书</h1>

<c:if test="${not empty book}">
  <form action="updateBook" method="post">
    <input type="hidden" name="id" value="${book.id}">
    ISBN：<input type="text" name="isbn" value="${book.isbn}"><br>
    书名：<input type="text" name="name" value="${book.name}"><br>
    价格：<input type="text" name="price" value="${book.price}"><br>
    <input type="submit" value="更新">
    <input type="button" value="取消" onclick="window.location.href='findBook'">
  </form>
</c:if>

<c:if test="${empty book}">
  <p>图书不存在或加载失败</p>
  <a href="findBook">返回图书列表</a>
</c:if>

</body>
</html>