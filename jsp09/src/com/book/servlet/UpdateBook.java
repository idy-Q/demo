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

@WebServlet("/updateBook")
public class UpdateBook extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("id");
        String isbn = request.getParameter("isbn");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        // 验证参数
        if (idStr == null || idStr.trim().isEmpty() ||
                isbn == null || isbn.trim().isEmpty() ||
                name == null || name.trim().isEmpty() ||
                priceStr == null || priceStr.trim().isEmpty()) {
            out.println("<script>alert('所有字段都不能为空'); history.back();</script>");
            return;
        }

        int id;
        float price;
        try {
            id = Integer.parseInt(idStr);
            price = Float.parseFloat(priceStr);
        } catch (NumberFormatException e) {
            out.println("<script>alert('无效的ID或价格格式'); history.back();</script>");
            return;
        }

        System.out.println("=== 开始更新图书 ===");
        System.out.println("图书ID: " + id + ", ISBN: " + isbn + ", 书名: " + name + ", 价格: " + price);

        Book book = new Book();
        book.setId(id);
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

            String sql = "UPDATE tb_book SET isbn = ?, name = ?, price = ? WHERE id = ?";
            System.out.println("SQL: " + sql);

            statement = connection.prepareStatement(sql);
            statement.setString(1, book.getIsbn());
            statement.setString(2, book.getName());
            statement.setFloat(3, book.getPrice());
            statement.setInt(4, book.getId());

            System.out.println("准备执行SQL更新...");
            int result = statement.executeUpdate();
            System.out.println("更新操作完成，影响行数: " + result);

            if (result > 0) {
                System.out.println("图书更新成功！");
            } else {
                System.out.println("图书更新失败，可能图书不存在");
                out.println("<script>alert('图书更新失败，可能图书不存在'); history.back();</script>");
                return;
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

        // 更新成功后重定向到图书列表
        System.out.println("重定向到图书列表页面");
        response.sendRedirect("findBook");
    }
}