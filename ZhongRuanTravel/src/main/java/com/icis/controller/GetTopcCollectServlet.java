package com.icis.controller;

import com.icis.utils.JedisUtil;
import redis.clients.jedis.Jedis;
import sun.awt.SunHints;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/getTopcCollect")
public class GetTopcCollectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jedis jedis = JedisUtil.getJedis();
        Set<String> keys = jedis.keys("collect:*");
        for (String key : keys) {
            Long scard = jedis.scard(key);
            String rid = key.split(":")[1];
            jedis.zadd("collectTop",scard,rid);
        }
        Set<String> collectTop = jedis.zrange("collectTop", 0, 9);
        for (String s : collectTop) {

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
