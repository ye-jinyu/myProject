package com.icis.dao.impl;

import com.icis.dao.RouteDao;
import com.icis.pojo.Route;
import com.icis.pojo.RouteImg;
import com.icis.pojo.Seller;
import com.icis.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RoutrDaoImpl implements RouteDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Route> queryRouteByCid(Integer cid,Integer currentPage,String rname) {
        String sql="SELECT * FROM tab_route WHERE 1=1";
        StringBuilder builder = new StringBuilder(sql);
        ArrayList params = new ArrayList();
        if (cid!=0){
            builder.append(" and cid=? ");
            params.add(cid);
        }
        if (rname!=null&&rname.length()>0){
            builder.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        builder.append(" limit ?,4 ");
        params.add((currentPage-1)*4);


        List<Route> routeList = jdbcTemplate.query(builder.toString(), new BeanPropertyRowMapper<>(Route.class),params.toArray());
        return routeList;
    }

    @Override
    public int queryRoute(Integer cid,String rname) {
        String sql="SELECT COUNT(*) FROM tab_route WHERE 1=1";
        StringBuilder builder = new StringBuilder(sql);
        ArrayList params = new ArrayList();
        if (cid!=0){
            builder.append(" and cid=? ");
            params.add(cid);
        }
        if (rname!=null&&rname.length()>0){
            builder.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        System.out.println(builder.toString());
        Integer totalRoute = jdbcTemplate.queryForObject(builder.toString(),Integer.class,params.toArray());
        return totalRoute;
    }

    @Override
    public Route getRouteDetailByRid(int rid) {
        String sql="SELECT * FROM tab_route WHERE  rid=?";
        List<Route> routeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class), rid);
        return routeList.get(0);
    }

    @Override
    public List<RouteImg> getRouteImg(int rid) {
        String sql="SELECT * FROM tab_route_img WHERE rid=?";
        List<RouteImg> routeImgList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RouteImg.class), rid);
        return routeImgList;
    }

    @Override
    public Seller getSellerBySid(int sid) {
        String sql="select * from tab_seller WHERE sid=?";
        List<Seller> sellerList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
        return sellerList.get(0);
    }

    @Override
    public List<Route> getRecomRouteByCid(String cid) {
        String sql="SELECT * FROM tab_route WHERE  cid=? limit 0,6";
        List<Route> routeList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Route.class), cid);
        return routeList;
    }

    @Override
    public List<Route> getHot() {
        String sql="SELECT * FROM (SELECT * FROM tab_route ORDER BY price ASC) a LIMIT 0,5";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }
}
