<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>添加信息</title></head>
<body>
<h1>添加信息</h1>

<form action="addBook" method="post">
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
    <input type="submit" value="提交保存">
</form>

</body>
</html>