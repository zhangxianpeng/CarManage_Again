package com.example.administrator.cm.activity.voilication;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cheshouye.api.client.WeizhangClient;
import com.cheshouye.api.client.json.ProvinceInfoJson;
import com.example.administrator.cm.R;
import com.example.administrator.cm.adapter.ListAdapter;
import com.example.administrator.cm.model.ListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ProvinceList extends Activity {
    private ListView lv_list;
     ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.csy_activity_citys);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.csy_titlebar);

        //标题
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("选择查询地-省份");

        //返回按钮
        Button btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lv_list = new ListView(this);

        lv_list = (ListView) findViewById(R.id.lv_1ist);



        mAdapter = new ListAdapter(this, getData2()) {
            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        };
        lv_list.setAdapter(mAdapter);

        lv_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView txt_name = (TextView) view.findViewById(R.id.txt_name);

                Intent intent = new Intent();
                intent.putExtra("province_name", txt_name.getText());
                intent.putExtra("province_id", txt_name.getTag().toString());

                intent.setClass(ProvinceList.this, CityList.class);
                startActivityForResult(intent, 20);
            }
        });

    }

    /**
     * title:获取省份信息
     *
     * @return
     */
    private List<ListModel> getData2() {

        List<ListModel> list = new ArrayList<ListModel>();
        List<ProvinceInfoJson> provinceList = WeizhangClient.getAllProvince();

        //开通数量提示
        TextView txtListTip = (TextView) findViewById(R.id.list_tip);

        txtListTip.setText("全国已开通"+provinceList.size()+"个省份, 其它省将陆续开放");

        for (ProvinceInfoJson provinceInfoJson : provinceList) {
            String provinceName = provinceInfoJson.getProvinceName();
            int provinceId = provinceInfoJson.getProvinceId();

            ListModel model = new ListModel();
            model.setTextName(provinceName);
            model.setNameId(provinceId);
            list.add(model);
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;
        Bundle bundle = data.getExtras();
        // 获取城市name
        String cityName = bundle.getString("city_name");
        String cityId = bundle.getString("city_id");

        Intent intent = new Intent();
        intent.putExtra("city_name", cityName);
        intent.putExtra("city_id", cityId);
        setResult(1, intent);
        finish();
    }
}
