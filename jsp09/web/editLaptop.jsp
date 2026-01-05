<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>修改信息</title></head>
<body>
<h1>修改信息</h1>

<form action="updateLaptop" method="post">
    <input type="hidden" name="id" value="${laptop.id}">

    <div>
        <label>型号：</label>
        <input type="text" name="modelNo" value="${laptop.modelNo}">
    </div>

    <div>
        <label>品牌：</label>
        <input type="text" name="brand" value="${laptop.brand}">
    </div>

    <div>
        <label>价格：</label>
        <input type="text" name="price" value="${laptop.price}">
    </div>

    <div>
        <label>库存：</label>
        <input type="text" name="stock" value="${laptop.stock}">
    </div>

    <br>
    <input type="submit" value="确认修改">
    <input type="button" value="返回" onclick="history.back()">
</form>

</body>
</html>