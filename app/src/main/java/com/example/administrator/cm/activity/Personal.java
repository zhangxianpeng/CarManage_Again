package com.example.administrator.cm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.example.administrator.cm.MainActivity;
import com.example.administrator.cm.R;
import com.example.administrator.cm.bean.Person;
import com.example.administrator.cm.utils.ToastUtil;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Personal extends AppCompatActivity {

    public static final int REGISTER_CODE = 1;

    EditText et_phone_login, et_pwd_login;
    Button btn_login;
    TextView tv_forget, tv_preview;

    private Switch remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_personal);
        //默认初始化
        Bmob.initialize(this, "c9d788e6da036be0240ab1804c84e2da");


        et_phone_login = (EditText) findViewById(R.id.et_phone_login);
        et_pwd_login = (EditText) findViewById(R.id.et_pwd_login);
        btn_login = (Button) findViewById(R.id.btn_ok_login);
        tv_forget = (TextView) findViewById(R.id.tv_register);
        tv_preview = (TextView) findViewById(R.id.tv_preview);
        remember = (Switch) findViewById(R.id.switch1);

        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Personal.this, Register.class), REGISTER_CODE);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryDao();
            }
        });
    }

    private void queryDao() {

        String phone = et_phone_login.getText().toString();
        String pwd = et_pwd_login.getText().toString();


        //查询单个用户
        BmobQuery<Person> query = new BmobQuery<Person>();

        query.addWhereEqualTo("user_phone", phone)
                .addWhereEqualTo("user_pwd", pwd);
        query.findObjects(new FindListener<Person>() {

            @Override
            public void done(List<Person> list, BmobException e) {
                if (e == null&&list.size()>0){
                    if (remember.isChecked()) {
                        Intent i = new Intent(Personal.this, MainActivity.class);
                        startActivity(i);
                    }else {
                        ToastUtil.ToastUtil(Personal.this,"请先打开记住密码选项");
                    }
                }
            }
        });

    }
}
