<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>修改信息</title></head>
<body>
<h1>修改信息</h1>

<form action="updateBook" method="post">
    <input type="hidden" name="id" value="${book.id}">

    <div>
        <label>ISBN：</label>
        <input type="text" name="isbn" value="${book.isbn}">
    </div>

    <div>
        <label>书名：</label>
        <input type="text" name="name" value="${book.name}">
    </div>

    <div>
        <label>价格：</label>
        <input type="text" name="price" value="${book.price}">
    </div>

    <br>
    <input type="submit" value="确认修改">
    <input type="button" value="返回" onclick="history.back()">
</form>

</body>
</html>