package com.example.administrator.cm.activity;



import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.cm.R;
import com.example.administrator.cm.adapter.RecyclerAdapter;
import com.example.administrator.cm.bean.GastStation;
import com.example.administrator.cm.utils.HttpCallbackListener;
import com.example.administrator.cm.utils.HttpUtil;
import com.example.administrator.cm.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PetrolStation extends AppCompatActivity
        implements View.OnClickListener, RecyclerAdapter.OnItemClickListener{
    private Button btn_city,btn_location;
    private TextView tv_petrolstation;
    private RecyclerView recyclerView;

    private RecyclerAdapter recyclerAdapter;
    private List<GastStation> gastStationList;



    public static final int SHOW_RESPONSE = 1;   //标志位


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    if (response != null) {
                        //接受到应答，解析Json数据
                        List<GastStation> gastStations = parseWithJSON(response);

                        gastStationList.addAll(gastStations);

                        recyclerAdapter.notifyDataSetChanged();

                        Log.v("1",response);
                        }
                    }

            }
        };

    private List<GastStation> parseWithJSON(String response) {
        List<GastStation> gastStations = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response).getJSONObject("result");
            JSONArray data = jsonObject.getJSONArray("data");

            GastStation gastStation;
            for(int i = 0; i<data.length(); i++){
                gastStation = new GastStation();
                JSONObject gastJson = data.getJSONObject(i);
                gastStation.setName(gastJson.getString("name"));
                gastStation.setAreaName(gastJson.getString("areaname"));
                gastStation.setAddress(gastJson.getString("address"));

                gastStations.add(gastStation);

                Log.v("2",gastStation.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gastStations;


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_station);

        gastStationList = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(this, gastStationList);

        initView();
    }

    private void initView() {
        btn_city = (Button) findViewById(R.id.btn_citysearch);
        btn_location = (Button)findViewById(R.id.btn_locationsearch);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerAdapter);


        btn_city.setOnClickListener(this);
        btn_location.setOnClickListener(this);

        recyclerAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_citysearch:
                getCityRequest();
                break;
            case R.id.btn_locationsearch:
                getLocationRequest();
                break;
                default:
                    break;
        }
    }


    //使用城市获取加油站列表
    private void getCityRequest() {
        String url = "http://apis.juhe.cn/oil/region?key=b77d71a00bf186387daf0cb4a4c1906d&format=2&city=%E5%8C%97%E4%BA%AC%E5%B8%82";
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
            }
        });

    }

    //使用定位点经纬度获取加油站列表信息
    private void getLocationRequest() {
    }



    @Override
    public void onItemClick(View v, int position) {
        ToastUtil.ToastUtil(this, position+"");
    }
}
