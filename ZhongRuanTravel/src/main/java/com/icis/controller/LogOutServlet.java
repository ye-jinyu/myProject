package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.ResultInfo;
import com.icis.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logOut")
public class LogOutServlet extends HttpServlet {
    private ObjectMapper mapper=new ObjectMapper();
    private Jedis jedis= JedisUtil.getJedis();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
        request.getSession().removeAttribute("name");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("remUsername".equals(cookie.getName())){
                Cookie remUsername = new Cookie("remUsername",null);
                remUsername.setMaxAge(0);
                response.addCookie(remUsername);

            }
            if ("remPassword".equals(cookie.getName())){
                System.out.println(22222);
                Cookie remPassword = new Cookie("remPassword",null);
                remPassword.setMaxAge(0);
                response.addCookie(remPassword);
            }
        }


        String dataJson = mapper.writeValueAsString(new ResultInfo(true));
        response.getWriter().write(dataJson);
        /*Long i = jedis.del("name");
        if (i==1){
            String dataJson = mapper.writeValueAsString(new ResultInfo(true));
            response.getWriter().write(dataJson);
        }else {
            String dataJson = mapper.writeValueAsString(new ResultInfo(false));
            response.getWriter().write(dataJson);
        }*/
    }
}
