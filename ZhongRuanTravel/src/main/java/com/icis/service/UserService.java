package com.icis.service;

import com.icis.pojo.User;

public interface UserService {
    public void addUsesr(User user);

    User queryUserByUsesrName(String username);
    int updateStatusByCode(String code);

}
