package com.book.servlet;

import com.book.model.Book;
import com.book.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/addBook")
public class AddBook extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        response.sendRedirect("addBook.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String isbn = request.getParameter("isbn");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        System.out.println("=== 开始添加图书 ===");
        System.out.println("接收到图书数据 - ISBN: " + isbn + ", 书名: " + name + ", 价格: " + priceStr);

        // 检查参数是否为空
        if (isbn == null || isbn.trim().isEmpty() ||
                name == null || name.trim().isEmpty() ||
                priceStr == null || priceStr.trim().isEmpty()) {
            System.out.println("ISBN、书名或价格为空");
            out.println("<script>alert('ISBN、书名和价格不能为空'); history.back();</script>");
            return;
        }

        float price;
        try {
            price = Float.parseFloat(priceStr);
        } catch (NumberFormatException e) {
            System.out.println("价格格式错误");
            out.println("<script>alert('价格格式不正确'); history.back();</script>");
            return;
        }

        Book book = new Book();
        book.setIsbn(isbn.trim());
        book.setName(name.trim());
        book.setPrice(price);

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            System.out.println("尝试获取数据库连接...");
            connection = JDBCUtil.getConnection();

            if(connection == null) {
                System.out.println("数据库连接为null！");
                out.println("<script>alert('数据库连接失败'); history.back();</script>");
                return;
            }

            System.out.println("数据库连接成功！");
            System.out.println("连接状态: " + (connection.isClosed() ? "已关闭" : "已连接"));

            String sql = "INSERT INTO tb_book (isbn, name, price) VALUES (?, ?, ?)";
            System.out.println("SQL: " + sql);

            statement = connection.prepareStatement(sql);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getName());
            statement.setFloat(3, book.getPrice());

            System.out.println("准备执行SQL插入...");
            int result = statement.executeUpdate();
            System.out.println("插入操作完成，影响行数: " + result);

            if (result > 0) {
                System.out.println("图书添加成功！");
            } else {
                System.out.println("图书添加失败，影响行数为0");
            }

        } catch (SQLException e) {
            System.out.println("SQL异常: " + e.getMessage());
            e.printStackTrace();
            out.println("<script>alert('数据库操作失败: " + e.getMessage() + "'); history.back();</script>");
            return;
        } catch (Exception e) {
            System.out.println("其他异常: " + e.getMessage());
            e.printStackTrace();
            out.println("<script>alert('系统错误: " + e.getMessage() + "'); history.back();</script>");
            return;
        } finally {
            JDBCUtil.release(connection, statement, null);
            System.out.println("资源已释放");
        }

        // 重定向到查询页面
        System.out.println("重定向到图书列表页面");
        response.sendRedirect("findBook");
    }
}