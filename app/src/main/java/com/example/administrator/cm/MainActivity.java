package com.example.administrator.cm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.administrator.cm.activity.CarManage;
import com.example.administrator.cm.activity.Location;
import com.example.administrator.cm.activity.PetrolStation;
import com.example.administrator.cm.bean.City;
import com.example.administrator.cm.utils.*;
import com.example.administrator.cm.activity.Personal;
import com.example.administrator.cm.activity.Voilicate;
import com.example.administrator.cm.service.musicService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    LinearLayout linearLayout;
    TextView tv_addoil, tv_carmanage, tv_voilication, tv_playmusic,tv_oiiprice;

    public static final int SHOW_RESPONSE = 1;   //标志位


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    if (response != null) {
                        //接受到应答，解析Json数据
                        List<City> cities = parseWithJSON(response);
                        for (City city:cities){
                            tv_oiiprice.append(city.getCity()+" "+city.getB90()+" "
                                    +city.getB93()+" "+city.getB97()+" "+city.getB0()+"\n");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };

    private List<City> parseWithJSON(String response) {
        List<City> citys = new ArrayList<>();
        try{
            //定义一个Json数组来存放所有信息
            JSONArray jsonArray = new JSONObject(response).getJSONArray("result");
            System.out.print(response);

            City city;
            for (int i=0;i<jsonArray.length();i++){
                JSONObject cityJson = jsonArray.getJSONObject(i);
                city = new City();
                city.setCity(cityJson.getString("city"));
                city.setB90(cityJson.getString("b90"));
                city.setB93(cityJson.getString("b93"));
                city.setB97(cityJson.getString("b97"));
                city.setB0(cityJson.getString("b0"));
                citys.add(city);


                /*  String b90  = jsonArray.getJSONObject(i);
                String b93  = jsonArray.getJSONObject(i);
                String b97  = jsonArray.getJSONObject(i);
                String b0  = jsonArray.getJSONObject(i);*/

                //System.out.print(city+b90+b93+b97+b0);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return citys;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(MainActivity.this, musicService.class);
        startService(intent);

        tv_oiiprice = (TextView) findViewById(R.id.tv_oilprice);
        tv_addoil = (TextView) findViewById(R.id.tv_addoil);
        tv_addoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "本功能即将上线", Toast.LENGTH_SHORT).show();
            }
        });
        tv_voilication = (TextView) findViewById(R.id.tv_voilication);
        tv_voilication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(MainActivity.this, Voilicate.class);
                startActivity(i3);
            }
        });
        tv_playmusic = (TextView) findViewById(R.id.tv_playmusic);
        tv_playmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ToastUtil.ToastUtil(MainActivity.this, "点击播放音乐");
            }
        });
        tv_carmanage = (TextView) findViewById(R.id.tv_carmanage);
        tv_carmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(MainActivity.this, CarManage.class);
                startActivity(i2);
            }
        });


        //工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //菜单头
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        linearLayout = (LinearLayout) navigationView.getHeaderView(0);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user = new Intent(MainActivity.this, Personal.class);
                startActivity(user);
            }
        });

        //桌面布局
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //导航栏
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //添加菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_petrol) {
          Intent petrolstation = new Intent(MainActivity.this, PetrolStation.class);
          startActivity(petrolstation);
        } else if (id == R.id.nav_location) {
            Intent location = new Intent(MainActivity.this, Location.class);
            startActivity(location);
        } else if (id == R.id.nav_oilprice) {
            //点击后异步请求网络参数
            getOilPrice();
        } else if (id == R.id.nav_share) {
            ToastUtil.ToastUtil(MainActivity.this, "分享");
        } else if (id == R.id.nav_exit) {
            finish();
            //onStop();
            System.exit(1);
            onStop();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getOilPrice() {
        String url = "http://apis.juhe.cn/cnoil/oil_city?key=e63393def5f8113c7f0c0289115bc988";
        HttpUtil.sendHttpRequest(url, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                //将服务器返回的结果存放到Message中
                message.obj = response.toString();
                handler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {
                System.out.println("获取相关信息失败");
                ToastUtil.ToastUtil(MainActivity.this,"获取相关信息失败");
            }
        });
    }

    //背景音乐停止
    @Override
    protected void onStop() {
        Intent intent = new Intent(MainActivity.this, musicService.class);
        stopService(intent);
        super.onStop();
    }

}
