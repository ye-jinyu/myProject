package com.icis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.pojo.ResultInfo;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;
import com.icis.utils.MailUtils;
import com.icis.utils.Md5Util;
import com.icis.utils.UuidUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    private ObjectMapper mapper=new ObjectMapper();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user=new User();
            String check = request.getParameter("check");
            String checkcode = (String) request.getSession().getAttribute("CHECKCODE_SERVER");
            if (checkcode.equalsIgnoreCase(check)){
                BeanUtils.populate(user,parameterMap);
                if (userService.queryUserByUsesrName(user.getUsername())==null){
                    String passwordMD5 = Md5Util.encodeByMd5(user.getPassword());
                    user.setPassword(passwordMD5);
                    String code = UuidUtil.getUuid();
                    user.setStatus("N");
                    user.setCode(code);
                    MailUtils.sendMail(user.getEmail(),"您已注册旅游网,点击链接激活 <a> http://192.168.4.28:8085/travel/registerUser?code="+code+"<a>","旅游网账号激活");
                    userService.addUsesr(user);
                    String dataJson = mapper.writeValueAsString(new ResultInfo(true));
                    response.getWriter().write(dataJson);
                }else {
                    String dataJson = mapper.writeValueAsString(new ResultInfo(false,"用户名已经存在"));
                    response.getWriter().write(dataJson);
                }

            }else{
                String dataJson = mapper.writeValueAsString(new ResultInfo(false,"验证码错误!"));
                response.getWriter().write(dataJson);
            }
        }catch (Exception e){
                response.sendRedirect("error/500.html");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code!=null){
            int i = userService.updateStatusByCode(code);
            if (i!=0){
                response.getWriter().write("激活成功!");
                return;
            }else {
                response.getWriter().write("激活失败!");
                return;
            }
        }
        this.doPost(request, response);

    }
}
