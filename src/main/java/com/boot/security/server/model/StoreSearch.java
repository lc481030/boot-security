package com.boot.security.server.model;

public class StoreSearch {

    private String productMode;
    private String startCity;
    private String targetCountry;
    private String afterCity;
    private String beginDay;
    private String endDay;
    private Integer pageSize;

    public String getProductMode() {
        return productMode;
    }

    public void setProductMode(String productMode) {
        this.productMode = productMode;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public String getAfterCity() {
        return afterCity;
    }

    public String getBeginDay() {
        return beginDay;
    }

    public String getEndDay() {
        return endDay;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getTargetCountry() {
        return targetCountry;
    }

    public void setAfterCity(String afterCity) {
        this.afterCity = afterCity;
    }

    public void setBeginDay(String beginDay) {
        this.beginDay = beginDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

    public void setTargetCountry(String targetCountry) {
        this.targetCountry = targetCountry;
    }
}

