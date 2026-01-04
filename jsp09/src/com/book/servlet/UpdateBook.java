package com.book.servlet;

import com.book.dao.BookDao;
import com.book.model.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateBook")
public class UpdateBook extends HttpServlet {

    private BookDao dao = new BookDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. 获取所有参数 (记得包含隐藏的 id)
        // 考试时：这里的参数名要根据你的题目改
        String idStr = request.getParameter("id");
        String isbn = request.getParameter("isbn");
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");

        // 2. 封装成对象
        Book book = new Book();
        try {
            book.setId(Integer.parseInt(idStr));
            book.setIsbn(isbn);
            book.setName(name);
            book.setPrice(Float.parseFloat(priceStr));

            // 3. 调用 DAO 更新
            dao.update(book);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4. 改完回首页
        response.sendRedirect("findBook");
    }
}