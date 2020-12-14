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

@WebServlet("/getRouteDetailByRid")
public class getRouteDetailByRidServlet extends HttpServlet {
    private RouteService routeService =new RouteServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        Route route= routeService.getRouteDetailByRid(rid);
        if (route!=null){
            String routeJson = mapper.writeValueAsString(route);
            response.getWriter().write(routeJson);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
