package com.icis.controller;

import com.icis.service.CategoryService;
import com.icis.service.UserService;
import com.icis.service.impl.CategoryServiceImpl;
import com.icis.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getCategory")
public class GetCategoryServlet extends HttpServlet {
    private CategoryService categoryService=new CategoryServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryJson = categoryService.getCategory();
        if (categoryJson!=null){
            response.getWriter().write(categoryJson);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
