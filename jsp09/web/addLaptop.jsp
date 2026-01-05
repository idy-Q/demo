<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>添加信息</title></head>
<body>
<h1>添加信息</h1>

<form action="addLaptop" method="post">
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
    <input type="submit" value="提交保存">
</form>

</body>
</html>