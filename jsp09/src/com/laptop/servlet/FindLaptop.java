package com.laptop.servlet;

import com.laptop.dao.LaptopDao;
import com.laptop.model.Laptop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findLaptop")
public class FindLaptop extends HttpServlet {
    // 实例化 DAO
    private LaptopDao dao = new LaptopDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1. 获取参数
        String keyword = request.getParameter("keyword");

        // 2. 调用 DAO 一行代码搞定
        List<Laptop> list = dao.findList(keyword);

        // 3. 存数据并转发
        request.setAttribute("laptopList", list);
        request.getRequestDispatcher("/listLaptop.jsp").forward(request, response);
    }
}