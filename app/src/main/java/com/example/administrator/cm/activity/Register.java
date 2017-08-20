package com.example.administrator.cm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.cm.R;
import com.example.administrator.cm.bean.Person;
import com.example.administrator.cm.utils.ToastUtil;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText et_phone_register,et_pwd_register,et_pwd_copy;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //默认初始化
        Bmob.initialize(this,"c9d788e6da036be0240ab1804c84e2da");


        // 获取界面中的相关View
        et_phone_register = (EditText) findViewById(R.id.et_phone_register);
        et_pwd_register = (EditText) findViewById(R.id.et_pwd_register);
        et_pwd_copy = (EditText) findViewById(R.id.et_pwd_copy);

        btnSignUp = (Button) findViewById(R.id.btn_ok_register);
        // 设置登录按钮点击事件
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // 获取用户输入的用户名和密码
        String userphone = et_phone_register.getText().toString();
        String password = et_pwd_register.getText().toString();
        String password_copy = et_pwd_copy.getText().toString();

        // 非空验证
        if(TextUtils.isEmpty(userphone) || TextUtils.isEmpty(password)||TextUtils.isEmpty(password_copy)){
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // 使用BmobSDK提供的注册功能
        Person user = new Person();
        user.setUser_phone(userphone);
        user.setUser_pwd(password);

        user.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {

                if(e == null){
                    ToastUtil.ToastUtil(Register.this,"用户注册成功，请登录");
                    Intent i = new Intent(Register.this,Personal.class);
                    startActivity(i);
                }else {
                    ToastUtil.ToastUtil(Register.this,"服务器繁忙，请重试");
                }
            }
        });

    }
}