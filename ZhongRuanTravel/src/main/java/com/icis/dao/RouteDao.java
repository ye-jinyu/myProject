package com.icis.dao;

import com.icis.pojo.Route;
import com.icis.pojo.RouteImg;
import com.icis.pojo.Seller;

import java.util.List;

public interface RouteDao {
    List<Route> queryRouteByCid(Integer cid,Integer currentPage,String rname);

    int queryRoute(Integer cid ,String rname);

    Route getRouteDetailByRid(int rid);

    List<RouteImg> getRouteImg(int rid);

    Seller getSellerBySid(int sid);
    List<Route> getRecomRouteByCid(String cid);

    List<Route> getHot();
}
