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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/findBook")
public class FindBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtil.getConnection();
            if(connection == null) {
                System.out.println("数据库连接失败！");
                request.setAttribute("error", "数据库连接失败");
                request.getRequestDispatcher("/listBook.jsp").forward(request, response);
                return;
            }

            String keyword = request.getParameter("keyword");
            String sql;

            if (keyword != null && !keyword.trim().isEmpty()) {
                // 搜索功能
                sql = "SELECT * FROM tb_book WHERE name LIKE ? OR isbn LIKE ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, "%" + keyword.trim() + "%");
                statement.setString(2, "%" + keyword.trim() + "%");
                System.out.println("执行搜索查询，关键词: " + keyword);
            } else {
                // 查询所有
                sql = "SELECT * FROM tb_book";
                statement = connection.prepareStatement(sql);
                System.out.println("执行全量查询");
            }

            resultSet = statement.executeQuery();

            while (resultSet.next()){
                Book book = new Book();
                int id = resultSet.getInt("id");
                String isbn = resultSet.getString("isbn");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");

                book.setId(id);
                book.setIsbn(isbn != null ? isbn : "");
                book.setName(name != null ? name : "");
                book.setPrice(price);
                books.add(book);
            }

            System.out.println("查询到 " + books.size() + " 条图书记录");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL异常: " + e.getMessage());
            request.setAttribute("error", "查询失败: " + e.getMessage());
        } finally {
            JDBCUtil.release(connection, statement, resultSet);
        }

        request.setAttribute("bookList", books);
        request.getRequestDispatcher("/listBook.jsp").forward(request, response);
    }
}