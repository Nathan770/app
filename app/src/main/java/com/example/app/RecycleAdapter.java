package com.example.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private ArrayList<ItemData> itemData;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView RecycleAdapter_IMG_pos;
        public TextView  RecycleAdapter_TXT_score;
        public TextView  RecycleAdapter_TXT_x;
        public TextView  RecycleAdapter_TXT_y;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             RecycleAdapter_IMG_pos = itemView.findViewById(R.id.data_IMG_winner);
             RecycleAdapter_TXT_score = itemView.findViewById(R.id.data_TXT_score);
             RecycleAdapter_TXT_x = itemView.findViewById(R.id.data_TXT_x);
             RecycleAdapter_TXT_y = itemView.findViewById(R.id.data_TXT_y);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.winner_data,parent,false);
         ViewHolder vh = new ViewHolder(v);
         return vh;
    }
    public RecycleAdapter(ArrayList<ItemData> itemDataList){
        itemData = itemDataList;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData currentItem = itemData.get(position);
        holder.RecycleAdapter_IMG_pos.setImageResource(currentItem.getimagePosition());
        holder.RecycleAdapter_TXT_score.setText(currentItem.getscore());
        holder.RecycleAdapter_TXT_x.setText(currentItem.getxText());
        holder.RecycleAdapter_TXT_y.setText(currentItem.getyText());
    }

    @Override
    public int getItemCount() {
        return itemData.size();
    }




}
