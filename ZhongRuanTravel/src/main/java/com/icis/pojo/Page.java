package com.icis.pojo;

import java.util.List;

public class Page {
    private List<Route> routeList;
    private Integer totalPage;
    private Integer currentPage;
    private Integer totalRoutes;
    private Integer pageSize;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<Route> getRouteList() {
        return routeList;
    }

    public void setRouteList(List<Route> routeList) {
        this.routeList = routeList;
    }


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalRoutes() {
        return totalRoutes;
    }

    public void setTotalRoutes(Integer totalRoutes) {
        this.totalRoutes = totalRoutes;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "routeList=" + routeList +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", totalRoutes=" + totalRoutes +
                ", pageSize=" + pageSize +
                '}';
    }
}
