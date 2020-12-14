package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.Route;
import com.icis.service.RouteService;
import com.icis.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/getRecomRouteByCid")
public class GetRecomRouteByCidServlet extends HttpServlet {
    private RouteService routeService=new RouteServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        List<Route> routeList = routeService.getRecomRouteByCid(cid);
        String routeListJson = mapper.writeValueAsString(routeList);
        response.getWriter().write(routeListJson);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
