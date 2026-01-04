package com.book.servlet;

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

@WebServlet("/deleteBook")
public class DeleteBook extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String idStr = request.getParameter("id");

        // 验证ID参数
        if (idStr == null || idStr.trim().isEmpty()) {
            out.println("<script>alert('图书ID不能为空'); history.back();</script>");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            out.println("<script>alert('无效的图书ID'); history.back();</script>");
            return;
        }

        System.out.println("=== 开始删除图书 ===");
        System.out.println("要删除的图书ID: " + id);

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

            String sql = "DELETE FROM tb_book WHERE id = ?";
            System.out.println("SQL: " + sql);

            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            System.out.println("准备执行SQL删除...");
            int result = statement.executeUpdate();
            System.out.println("删除操作完成，影响行数: " + result);

            if (result > 0) {
                System.out.println("图书删除成功！");
            } else {
                System.out.println("图书删除失败，可能图书不存在");
                out.println("<script>alert('图书删除失败，可能图书不存在'); history.back();</script>");
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

        // 删除成功后重定向到图书列表
        System.out.println("重定向到图书列表页面");
        response.sendRedirect("findBook");
    }
}