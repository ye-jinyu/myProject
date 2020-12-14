import com.alibaba.druid.support.spring.stat.SpringStatUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icis.dao.RouteDao;
import com.icis.dao.UserDao;
import com.icis.dao.impl.RoutrDaoImpl;
import com.icis.dao.impl.UserDaoImpl;
import com.icis.pojo.Route;
import com.icis.pojo.User;
import com.icis.service.UserService;
import com.icis.service.impl.UserServiceImpl;
import com.icis.utils.JDBCUtils;
import com.icis.utils.JedisUtil;
import org.junit.Before;
import org.springframework.jdbc.core.JdbcTemplate;
import redis.clients.jedis.Jedis;

import java.util.List;

public class Test {
    @Before
    public void c(){

    }
    @org.junit.Test
    public void test(){
        UserDao userDao=new UserDaoImpl();
        User user=new User();
        user.setUsername("12123");
        user.setPassword("2333");
        user.setName("看看");
        userDao.addUser(user);
    }
    @org.junit.Test
    public void test1(){
        JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
        System.out.println(jdbcTemplate);
    }
    @org.junit.Test
    public void test2(){
        UserDao userDao=new UserDaoImpl();
        UserService userService=new UserServiceImpl();
        User user=new User();
        user.setUsername("123");
        user.setPassword("2333");
        user.setName("看看");
        User userList = userService.queryUserByUsesrName(user.getUsername());
        System.out.println(userList);

    }
    @org.junit.Test
    public void test3() throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        Jedis jedis = JedisUtil.getJedis();

        String name = jedis.get("name");
        String string = mapper.writeValueAsString(name);
        System.out.println(name);

    }

}
