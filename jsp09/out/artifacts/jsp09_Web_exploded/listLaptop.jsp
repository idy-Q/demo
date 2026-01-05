<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>列表管理</title>
    <style>
        /* 简单美化，考试时可不写 */
        table { width: 80%; border-collapse: collapse; margin: 20px 0; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: center; }
        th { background: #f0f0f0; }
    </style>
</head>
<body>

<h1>信息列表</h1>
<div>
    <a href="addLaptop.jsp">添加新数据</a>
    <div style="margin-bottom: 10px; float: right;">
        <form action="findLaptop" method="get" style="display:inline;">
            <input type="text" name="keyword" value="${param.keyword}" placeholder="输入关键词搜索...">

            <input type="submit" value="搜索">

            <input type="button" value="显示全部" onclick="location.href='findLaptop'">
        </form>
    </div>
</div>

<table>
    <tr>
        <th>ID</th>
        <th>型号</th>
        <th>品牌</th>
        <th>价格</th>
        <th>库存</th>
        <th>操作</th>
    </tr>

    <c:forEach var="item" items="${laptopList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.modelNo}</td>
            <td>${item.brand}</td>
            <td>${item.price}</td>
            <td>${item.stock}</td>
            <td>
                <a href="editLaptop?id=${item.id}">修改</a>
                <a href="deleteLaptop?id=${item.id}" onclick="return confirm('确定删除吗？')">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>