package com.book.servlet;

import com.book.dao.LaptopDao;
import com.book.model.Laptop;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addLaptop")
public class AddLaptop extends HttpServlet {
    private LaptopDao dao = new LaptopDao();

    // doGet 只负责跳转页面，不变
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("addLaptop.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. 接收参数 (考试时主要改这里)
        String modelNo = request.getParameter("modelNo");
        String brand = request.getParameter("brand");
        String priceStr = request.getParameter("price");
        String stock = request.getParameter("stock");

        // 2. 封装对象
        Laptop laptop = new Laptop();
        laptop.setModelNo(modelNo);
        laptop.setBrand(brand);
        laptop.setStock(Integer.parseInt(stock));
        // 简单处理数字转换，考试可以直接 throws Exception 让它报错也行，或者用个 try catch
        try {
            laptop.setPrice(Float.parseFloat(priceStr));
        } catch (NumberFormatException e) {
            laptop.setPrice(0);
        }

        // 3. 调用 DAO 保存
        dao.add(laptop);

        // 4. 跳转
        response.sendRedirect("findLaptop");
    }
}