package com.example.administrator.cm.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by Administrator on 2017/5/14.
 * 用户登录模型
 * 1，张现鹏，123,15277397109，男，302996244@qq.com
 */

public class Person extends BmobObject{


    private String user_id;
    private String user_name;
    private String user_pwd;
    private String user_phone;
    private String user_sex;
    private String user_email;

    //用户头像
    BmobFile img_user;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public BmobFile getPhoto() {
        return img_user;
    }

    public void setPhoto(BmobFile photo) {
        img_user = photo;
    }

    @Override
    public String toString() {
        return "Person [user_phone=" + user_phone + ", user_name=" + user_name
                + ", user_pwd=" + user_pwd + "]";
    }


}
