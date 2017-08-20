package com.example.administrator.cm.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/19.
 * 加油订单模型
 */

public class Addoil extends BmobObject{
    private String car_id;
    private String oil_id;
    private String oil_location_id;
    private String oil_much;
    private String oil_money;
    private String oil_time;
    private String oil_type;
    private String oil_perprice;

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getOil_id() {
        return oil_id;
    }

    public void setOil_id(String oil_id) {
        this.oil_id = oil_id;
    }

    public String getOil_location_id() {
        return oil_location_id;
    }

    public void setOil_location_id(String oil_location_id) {
        this.oil_location_id = oil_location_id;
    }

    public String getOil_much() {
        return oil_much;
    }

    public void setOil_much(String oil_much) {
        this.oil_much = oil_much;
    }

    public String getOil_money() {
        return oil_money;
    }

    public void setOil_money(String oil_money) {
        this.oil_money = oil_money;
    }

    public String getOil_time() {
        return oil_time;
    }

    public void setOil_time(String oil_time) {
        this.oil_time = oil_time;
    }

    public String getOil_type() {
        return oil_type;
    }

    public void setOil_type(String oil_type) {
        this.oil_type = oil_type;
    }

    public String getOil_perprice() {
        return oil_perprice;
    }

    public void setOil_perprice(String oil_perprice) {
        this.oil_perprice = oil_perprice;
    }


}
