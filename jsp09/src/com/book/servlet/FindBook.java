package com.book.servlet;

import com.book.dao.BookDao;
import com.book.model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findBook")
public class FindBook extends HttpServlet {
    // 实例化 DAO
    private BookDao dao = new BookDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. 获取参数
        String keyword = request.getParameter("keyword");

        // 2. 调用 DAO 一行代码搞定
        List<Book> list = dao.findList(keyword);

        // 3. 存数据并转发
        request.setAttribute("bookList", list);
        request.getRequestDispatcher("/listBook.jsp").forward(request, response);
    }
}