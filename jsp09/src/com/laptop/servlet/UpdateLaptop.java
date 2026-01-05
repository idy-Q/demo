package com.laptop.servlet;

import com.laptop.dao.LaptopDao;
import com.laptop.model.Laptop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateLaptop")
public class UpdateLaptop extends HttpServlet {

    private LaptopDao dao = new LaptopDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. 获取所有参数 (记得包含隐藏的 id)
        // 考试时：这里的参数名要根据你的题目改
        String idStr = request.getParameter("id");
        String modelNo = request.getParameter("modelNo");
        String brand = request.getParameter("brand");
        String priceStr = request.getParameter("price");
        String stock = request.getParameter("stock");

        // 2. 封装成对象
        Laptop laptop = new Laptop();
        try {
            laptop.setId(Integer.parseInt(idStr));
            laptop.setModelNo(modelNo);
            laptop.setBrand(brand);
            laptop.setPrice(Float.parseFloat(priceStr));
            laptop.setStock(Integer.parseInt(stock));

            // 3. 调用 DAO 更新
            dao.update(laptop);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4. 改完回首页
        response.sendRedirect("findLaptop");
    }
}