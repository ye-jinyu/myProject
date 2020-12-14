package com.icis.dao;

import com.icis.pojo.Route;
import com.icis.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> queryUserByUsesrName(String username);

    void addUser(User user);

    List<User> queryUserByCode(String code);

    int updateStatusByCode(String code);

    List<Route> getRecomRouteByCid(String cid);
}
