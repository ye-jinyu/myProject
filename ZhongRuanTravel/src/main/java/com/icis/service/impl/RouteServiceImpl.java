package com.icis.service.impl;

import com.icis.dao.RouteDao;
import com.icis.dao.UserDao;
import com.icis.dao.impl.RoutrDaoImpl;
import com.icis.pojo.Page;
import com.icis.pojo.Route;
import com.icis.pojo.RouteImg;
import com.icis.pojo.Seller;
import com.icis.service.RouteService;

import java.lang.annotation.Target;
import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao=new RoutrDaoImpl();
    @Override
    public Page queryRouteByCidCurrentPage(String _cid, String _currentPage,String rname) {
        Page page=new Page();
        Integer cid=0;
        if (!_cid.equals("null") && _cid.length()>0) {
            cid = Integer.parseInt(_cid);
        }
        Integer currentPage=1;
        if (_currentPage!=null&&_currentPage.length()>0){
            currentPage=Integer.parseInt(_currentPage);
        }
        if (currentPage<1){
            currentPage=1;
        }
        int totalRoute=routeDao.queryRoute(cid,rname);
        System.out.println(totalRoute);
        page.setTotalRoutes(totalRoute);
        int totalPage=totalRoute/4;
        if (totalRoute%4!=0){
          totalPage=totalPage+1;
        }
        page.setTotalPage(totalPage);
        if (currentPage>totalPage&&totalPage!=0){
            currentPage=totalPage;
        }
        page.setPageSize(4);
        page.setCurrentPage(currentPage);
        List<Route> routeList = routeDao.queryRouteByCid(cid, currentPage,rname);
        page.setRouteList(routeList);

        return page;
    }

    @Override
    public Route getRouteDetailByRid(String _rid) {
        int rid = Integer.parseInt(_rid);
        Route route = routeDao.getRouteDetailByRid(rid);
        int sid = route.getSid();
        Seller seller= routeDao.getSellerBySid(sid);
        route.setSeller(seller);
        List<RouteImg> routeImgList= routeDao.getRouteImg(rid);
        route.setRouteImgList(routeImgList);
        return route;

    }

    @Override
    public List<Route> getRecomRouteByCid(String cid) {
        return routeDao.getRecomRouteByCid(cid);
    }

    @Override
    public List<Route> getHot() {
        return routeDao.getHot();
    }
}
