package com.icis.controller;

import com.icis.utils.JDBCUtils;
import com.icis.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/getLogName")
public class GetLogNameServlet extends HttpServlet {
    private Jedis jedis= JedisUtil.getJedis();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
        String name=(String) request.getSession().getAttribute("name");
        //String name = jedis.get("name");
        if (name!=null){
            response.getWriter().write(name);
        }
    }
}
