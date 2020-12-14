package com.icis.controller;

import com.icis.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/uncollect")
public class UncollectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jedis jedis = JedisUtil.getJedis();
        HttpSession session = request.getSession();
        String rid = request.getParameter("rid");

        Integer  uid = (Integer) session.getAttribute("uid");
        System.out.println(uid);
        if (uid!=null){
            String key="collect:"+rid;
            jedis.srem(key, String.valueOf(uid));

            Long scard = jedis.scard(key);

            response.getWriter().write(String.valueOf(scard));
        }else {

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
