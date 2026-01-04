package com.book.servlet;

import com.book.dao.BookDao;
import com.book.model.Book;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addBook")
public class AddBook extends HttpServlet {
    private BookDao dao = new BookDao();

    // doGet 只负责跳转页面，不变
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("addBook.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. 接收参数 (考试时主要改这里)
        String isbn = request.getParameter("isbn");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        // 2. 封装对象
        Book book = new Book();
        book.setIsbn(isbn);
        book.setName(name);
        // 简单处理数字转换，考试可以直接 throws Exception 让它报错也行，或者用个 try catch
        try {
            book.setPrice(Float.parseFloat(priceStr));
        } catch (NumberFormatException e) {
            book.setPrice(0);
        }

        // 3. 调用 DAO 保存
        dao.add(book);

        // 4. 跳转
        response.sendRedirect("findBook");
    }
}