package com.icis.dao.impl;


import com.icis.dao.CategoryDao;
import com.icis.pojo.Category;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Category> getCategory() {
        String sql="SELECT * FROM tab_category ORDER BY cid";
        List<Category> categoryList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Category.class));
        return categoryList;
    }
}
