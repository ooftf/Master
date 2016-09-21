package com.master.kit.testcase.json;

/**
 * Created by master on 2016/4/19.
 */
public class City {

    /**
     * CityId : 18
     * CityName : 西安
     * ProvinceId : 27
     * CityOrder : 1
     */

    private int CityId;
    private String CityName;
    private int ProvinceId;
    private int CityOrder;

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int CityId) {
        this.CityId = CityId;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public int getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int ProvinceId) {
        this.ProvinceId = ProvinceId;
    }

    public int getCityOrder() {
        return CityOrder;
    }

    public void setCityOrder(int CityOrder) {
        this.CityOrder = CityOrder;
    }
}
