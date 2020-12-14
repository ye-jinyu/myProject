package com.icis.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.dao.CategoryDao;
import com.icis.dao.impl.CategoryDaoImpl;
import com.icis.pojo.Category;
import com.icis.service.CategoryService;
import com.icis.utils.JedisUtil;
import redis.clients.jedis.Jedis;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private ObjectMapper mapper=new ObjectMapper();
    private Jedis jedis= JedisUtil.getJedis();
    private CategoryDao categoryDao=new CategoryDaoImpl();
    @Override
    public String getCategory() {
        try {
                List<Category> categoryList= categoryDao.getCategory();
                String categoryJson = mapper.writeValueAsString(categoryList);
            return categoryJson;
        } catch (JsonProcessingException e) {
            return null;
        }

    }
}
