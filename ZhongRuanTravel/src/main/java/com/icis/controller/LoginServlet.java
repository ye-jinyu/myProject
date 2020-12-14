package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.ResultInfo;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;
import com.icis.utils.JedisUtil;
import com.icis.utils.Md5Util;
import org.apache.commons.beanutils.BeanUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    private Jedis jedis = JedisUtil.getJedis();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user=new User();
            String check = request.getParameter("check");
            String checkcode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
            if (checkcode.equalsIgnoreCase(check)){
                BeanUtils.populate(user,parameterMap);
                User querUser=userService.queryUserByUsesrName(user.getUsername());
                if (querUser!=null){
                    if ("Y".equals(querUser.getStatus())){
                        String passwordMd5 = Md5Util.encodeByMd5(user.getPassword());
                        if (passwordMd5.equals(querUser.getPassword())){
                            String autoLog = request.getParameter("autoLog");
                            String remPass = request.getParameter("remPass");
                            if ("on".equals(autoLog)){
                                Cookie username = new Cookie("username",user.getUsername());
                                Cookie password = new Cookie("password",user.getPassword());
                                username.setMaxAge(60 * 60 * 24 * 30);
                                password.setMaxAge(60 * 60 * 24 * 30);
                                response.addCookie(username);
                                response.addCookie(password);
                            }else {
                                Cookie username = new Cookie("username","");
                                Cookie password = new Cookie("password","");
                                response.addCookie(username);
                                response.addCookie(password);
                            }
                            if ("on".equals(remPass)){
                                Cookie remUsername = new Cookie("remUsername",user.getUsername());
                                Cookie remPassword = new Cookie("remPassword",user.getPassword());
                                remUsername.setMaxAge(60 * 60 * 24 * 30);
                                remPassword.setMaxAge(60 * 60 * 24 * 30);
                                response.addCookie(remUsername);
                                response.addCookie(remPassword);
                            }

                            request.getSession().setAttribute("name",querUser.getName());
                            request.getSession().setAttribute("uid",querUser.getUid());
                            //jedis.set("name",querUser.getName());
                            String dataJson = mapper.writeValueAsString(new ResultInfo(true));
                            response.getWriter().write(dataJson);
                        }else {
                            String dataJson = mapper.writeValueAsString(new ResultInfo(false,"密码错误!"));
                            response.getWriter().write(dataJson);
                        }

                    }else {
                        String dataJson = mapper.writeValueAsString(new ResultInfo(false,"账号未激活!"));
                        response.getWriter().write(dataJson);
                    }
                }else {
                    String dataJson = mapper.writeValueAsString(new ResultInfo(false,"用户名错误!"));
                    response.getWriter().write(dataJson);
                }

            }else {
                String dataJson = mapper.writeValueAsString(new ResultInfo(false,"验证码错误!"));
                response.getWriter().write(dataJson);
            }
        }catch (Exception e){
            response.sendRedirect("error/500.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        boolean usernameflag=false;
        boolean passwordflag=false;
        User user=new User();
        if (cookies!=null&&cookies.length>2){
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())){
                    user.setUsername(cookie.getValue());
                    usernameflag=true;
                }
                if ("password".equals(cookie.getName())){
                    user.setPassword(cookie.getValue());
                    passwordflag=true;
                }
            }
            if (usernameflag&&passwordflag){
                String userJson = mapper.writeValueAsString(user);
                response.getWriter().write(userJson);
            }
        }



    }
}
