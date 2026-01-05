package com.laptop.servlet;

import com.laptop.dao.LaptopDao;
import com.laptop.model.Laptop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editLaptop")
public class EditLaptop extends HttpServlet {

    // 实例化 DAO
    private LaptopDao dao = new LaptopDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 获取 ID
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.trim().isEmpty()) {
            // 2. 调用 DAO 查出旧数据
            // 考试时：这里把 Book 换成你的实体类 (如 Student)
            Laptop laptop = dao.getById(Integer.parseInt(idStr));

            // 3. 把数据存进去，转发给 JSP 回显
            request.setAttribute("laptopList", laptop);
            request.getRequestDispatcher("/editLaptop.jsp").forward(request, response);
        } else {
            response.sendRedirect("findLaptop");
        }
    }
}