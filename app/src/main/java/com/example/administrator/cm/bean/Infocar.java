package com.example.administrator.cm.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/5/19.
 * 个人名下车辆信息
 * id、车牌、品牌、所有者
 * ID = 1 ，车牌 = 桂R35377 ，品牌 = 五菱宏光 ， 所有者 = 张现鹏
 */

public class Infocar extends BmobObject {
    private String  car_id;
    private String car_chepai;
    private String car_brand;
    private String car_owner;

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public String getCar_chepai() {
        return car_chepai;
    }

    public void setCar_chepai(String car_chepai) {
        this.car_chepai = car_chepai;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_owner() {
        return car_owner;
    }

    public void setCar_owner(String car_owner) {
        this.car_owner = car_owner;
    }


}
