package com.icis.service;

import com.icis.pojo.Page;
import com.icis.pojo.Route;

import java.util.List;

public interface RouteService {

    Page queryRouteByCidCurrentPage(String cid,String currentPage,String rname);

    Route getRouteDetailByRid(String rid);

    List<Route> getRecomRouteByCid(String cid);

    List<Route> getHot();
}
