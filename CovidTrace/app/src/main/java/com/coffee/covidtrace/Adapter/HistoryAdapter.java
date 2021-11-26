package com.coffee.covidtrace.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.R;

import org.w3c.dom.Text;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    String [] place_list, check_in_out;

    public HistoryAdapter(String [] place_list, String []check_in_out){
        this.place_list = place_list;
        this.check_in_out = check_in_out;
    }
    public HistoryAdapter(String [] place_list){
        this.place_list = place_list;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAdapter.HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.tv_check_in_out.setText(check_in_out[position]);
        holder.tv_location_enter.setText(place_list[position]);
    }


    @Override
    public int getItemCount() {
        return place_list.length;
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_check_in_out, tv_location_enter, tv_time_record;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_check_in_out = itemView.findViewById(R.id.tv_check_in_out);
            tv_location_enter = itemView.findViewById(R.id.tv_location_enter);
            tv_time_record = itemView.findViewById(R.id.tv_date_time);
            imageView = itemView.findViewById(R.id.iv_location);
        }
    }
}