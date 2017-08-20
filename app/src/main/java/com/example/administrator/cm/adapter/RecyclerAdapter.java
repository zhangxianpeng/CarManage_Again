package com.example.administrator.cm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.cm.R;
import com.example.administrator.cm.bean.GastStation;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter implements View.OnClickListener{

    private Context context;
    private List<GastStation> gastStations;
    private OnItemClickListener listener;

    public RecyclerAdapter(Context context, List<GastStation> gastStations) {
        this.context = context;
        this.gastStations = gastStations;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gast_station, parent, false);

        view.setOnClickListener(this);

        ItemViewHolder viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder)holder;

        viewHolder.itemView.setTag(position);

        GastStation gastStation = gastStations.get(position);

        viewHolder.tvContent.setText(gastStation.getName()+"\n"+gastStation.getAreaName()+"\n"+gastStation.getAddress());
    }

    @Override
    public int getItemCount() {
        return gastStations.size();
    }


    private static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tvContent;


        public ItemViewHolder(View itemView) {
            super(itemView);

            tvContent = (TextView)itemView.findViewById(R.id.tv_content);
        }
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    public interface OnItemClickListener{

        void onItemClick(View v, int position);
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClick(v, (int)v.getTag());

    }
}
