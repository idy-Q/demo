package com.book.servlet;

import com.book.dao.BookDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBook")
public class DeleteBook extends HttpServlet {
    private BookDao dao = new BookDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        if(idStr != null){
            dao.delete(Integer.parseInt(idStr));
        }
        response.sendRedirect("findBook");
    }
}