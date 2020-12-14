package com.icis.service.impl;

import com.icis.dao.UserDao;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.User;
import com.icis.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public void addUsesr(User user) {
        userDao.addUser(user);
    }

    @Override
    public User queryUserByUsesrName(String username) {
        List<User> userList = userDao.queryUserByUsesrName(username);
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }else {
            return null;
        }
    }


    @Override
    public int updateStatusByCode(String code) {
       return userDao.updateStatusByCode(code);
    }
}
