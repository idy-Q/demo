package com.laptop.servlet;

import com.laptop.dao.LaptopDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteLaptop")
public class DeleteLaptop extends HttpServlet {
    private LaptopDao dao = new LaptopDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        if(idStr != null){
            dao.delete(Integer.parseInt(idStr));
        }
        response.sendRedirect("findLaptop");
    }
}