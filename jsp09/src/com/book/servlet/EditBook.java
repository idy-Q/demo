package com.book.servlet;

import com.book.model.Book;
import com.book.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/editBook")
public class EditBook extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");

        // 验证ID参数
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect("findBook");
            return;
        }

        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("findBook");
            return;
        }

        System.out.println("=== 加载图书编辑页面 ===");
        System.out.println("要编辑的图书ID: " + id);

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Book book = null;

        try {
            connection = JDBCUtil.getConnection();
            if(connection == null) {
                System.out.println("数据库连接失败！");
                response.sendRedirect("findBook");
                return;
            }

            String sql = "SELECT * FROM tb_book WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setName(resultSet.getString("name"));
                book.setPrice(resultSet.getFloat("price"));
                System.out.println("找到图书: " + book.getName());
            } else {
                System.out.println("图书不存在，ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("SQL异常: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }

        request.setAttribute("book", book);
        request.getRequestDispatcher("/editBook.jsp").forward(request, response);
    }
}