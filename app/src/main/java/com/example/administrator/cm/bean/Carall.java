package com.example.administrator.cm.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/19.
 * 单辆汽车状态信息
 *id，剩余油量，已行驶里程数，发动机状态，车灯状态，变速器状态
 */

public class Carall extends BmobObject {
    private String car_id;
    private String car_rest_oil;
    private String car_km;
    private String car_engine;
    private String car_light;
    private String car_transmission;

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getCar_rest_oil() {
        return car_rest_oil;
    }

    public void setCar_rest_oil(String car_rest_oil) {
        this.car_rest_oil = car_rest_oil;
    }

    public String getCar_km() {
        return car_km;
    }

    public void setCar_km(String car_km) {
        this.car_km = car_km;
    }

    public String getCar_engine() {
        return car_engine;
    }

    public void setCar_engine(String car_engine) {
        this.car_engine = car_engine;
    }

    public String getCar_light() {
        return car_light;
    }

    public void setCar_light(String car_light) {
        this.car_light = car_light;
    }

    public String getCar_transmission() {
        return car_transmission;
    }

    public void setCar_transmission(String car_transmission) {
        this.car_transmission = car_transmission;
    }



}
