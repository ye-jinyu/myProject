package com.icis.dao.impl;

import com.icis.dao.UserDao;
import com.icis.pojo.Route;
import com.icis.pojo.User;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<User> queryUserByUsesrName(String username) {
        String sql="SELECT * FROM tab_user WHERE username=?";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class),username);
        return userList;
    }

    @Override
    public void addUser(User user) {
        String sql="INSERT tab_user VALUES (NULL,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),user.getName(),user.getBirthday(),
                user.getSex(),user.getTelephone(),user.getEmail(),user.getStatus(),user.getCode());
    }

    @Override
    public List<User> queryUserByCode(String code) {
        String sql="";
        List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
        return userList;
    }

    @Override
    public int updateStatusByCode(String code) {
        String sql="UPDATE tab_user SET status=? WHERE code=?";
        int i = jdbcTemplate.update(sql, "Y", code);
        return i;
    }

    @Override
    public List<Route> getRecomRouteByCid(String cid) {
        return null;
    }
}
