package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/getCollectNumber")
public class GetCollectNumberServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jedis jedis = JedisUtil.getJedis();
        HttpSession session = request.getSession();
        String rid = request.getParameter("rid");
        Integer  uid = (Integer) session.getAttribute("uid");
        String key="collect:"+rid;
        Long scard = jedis.scard(key);
        Boolean sismember=false;
        if (uid!=null) {
            sismember = jedis.sismember(key, String.valueOf(uid));
        }
        ObjectMapper objectMapper =new ObjectMapper();
        String dataJson="{\"scard\":"+scard+",\"sismember\":"+sismember+"}";
        response.getWriter().write(String.valueOf(dataJson));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
