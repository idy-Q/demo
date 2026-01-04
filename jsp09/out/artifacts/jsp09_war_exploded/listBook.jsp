<%--
  Created by IntelliJ IDEA.
  User: TR
  Date: 2025/11/7/0007
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书列表</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        .actions { white-space: nowrap; }
        .actions button { margin-right: 5px; padding: 4px 8px; cursor: pointer; }
    </style>
    <script>
        function confirmDelete(name, id) {
            if(confirm('确定要删除图书 \"' + name + '\" 吗？')) {
                window.location.href = 'deleteBook?id=' + id;
            }
        }

        function editBook(id) {
            window.location.href = 'editBook?id=' + id;
        }
    </script>
</head>
<body>
<h1>图书列表</h1>

<a href="addBook.jsp">添加新图书</a>

<!-- 搜索表单 -->
<form action="findBook" method="get" style="margin: 15px 0;">
    <input type="text" name="keyword" placeholder="输入书名或ISBN搜索" value="${param.keyword}">
    <input type="submit" value="搜索">
    <a href="findBook">显示全部</a>
</form>

<table>
    <tr>
        <th>ID</th>
        <th>ISBN</th>
        <th>书名</th>
        <th>价格</th>
        <th>操作</th>
    </tr>
    <c:forEach var="book" items="${bookList}">
        <tr>
            <td>${book.id}</td>
            <td>${book.isbn}</td>
            <td>${book.name}</td>
            <td>${book.price}</td>
            <td class="actions">
                <button onclick="editBook(${book.id})">修改</button>
                <button onclick="confirmDelete('${book.name}', ${book.id})">删除</button>
            </td>
        </tr>
    </c:forEach>
</table>

<c:if test="${empty bookList}">
    <p>没有找到图书数据</p>
</c:if>

<a href="index.jsp">返回首页</a>
</body>
</html>