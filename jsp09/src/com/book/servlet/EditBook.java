package com.book.servlet;

import com.book.dao.BookDao;
import com.book.model.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editBook")
public class EditBook extends HttpServlet {

    // 实例化 DAO
    private BookDao dao = new BookDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取 ID
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.trim().isEmpty()) {
            // 2. 调用 DAO 查出旧数据
            // 考试时：这里把 Book 换成你的实体类 (如 Student)
            Book book = dao.getById(Integer.parseInt(idStr));

            // 3. 把数据存进去，转发给 JSP 回显
            request.setAttribute("book", book);
            request.getRequestDispatcher("/editBook.jsp").forward(request, response);
        } else {
            response.sendRedirect("findBook");
        }
    }
}