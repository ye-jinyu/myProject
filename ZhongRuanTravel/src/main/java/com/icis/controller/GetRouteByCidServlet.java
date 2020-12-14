package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.Page;
import com.icis.service.RouteService;
import com.icis.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getRouteByCid")
public class GetRouteByCidServlet extends HttpServlet {
    private ObjectMapper mapper=new ObjectMapper();
    private RouteService routeService=new RouteServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");
        String rname = request.getParameter("rname");
        Page page= routeService.queryRouteByCidCurrentPage(cid,currentPage,rname);
        String pageJson = mapper.writeValueAsString(page);
        response.getWriter().write(pageJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
