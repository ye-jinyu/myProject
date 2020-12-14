package com.icis.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.ResultInfo;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;
import com.icis.utils.Md5Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 解决全站乱码问题，处理所有的请求
 */
@WebFilter("/*")
public class CharchaterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain) throws IOException, ServletException {
        //将父接口转为子接口
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;
        //获取请求方法
        String method = request.getMethod();
        //解决post请求中文数据乱码问题
        if(method.equalsIgnoreCase("post")){
            request.setCharacterEncoding("utf-8");
        }
        //处理响应乱码
        response.setContentType("text/html;charset=utf-8");

        Cookie[] cookies = request.getCookies();
        User user=new User();
        if (cookies!=null&&cookies.length>2){
            for (Cookie cookie : cookies) {
                if ("remUsername".equals(cookie.getName())){
                    user.setUsername(cookie.getValue());
                }
                if ("remPassword".equals(cookie.getName())){
                    user.setPassword(cookie.getValue());
                }
            }
            if (user.getUsername()!=null&&user.getPassword()!=null){
                UserService userService =new UserServiceImpl();
                User querUser=userService.queryUserByUsesrName(user.getUsername());

                try {
                    String s = Md5Util.encodeByMd5(user.getPassword());
                    if (s.equals(querUser.getPassword())){
                        request.getSession().setAttribute("name",querUser.getName());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }


        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
