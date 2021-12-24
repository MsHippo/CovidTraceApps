package com.coffee.covidtrace.Adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.R;

import org.w3c.dom.Text;

public class HistoryAdapter extends ListAdapter<History, HistoryAdapter.HistoryViewHolder> {

//    String [] place_list, check_in_out;

//    public HistoryAdapter(String [] place_list, String []check_in_out){
//        this.place_list = place_list;
//        this.check_in_out = check_in_out;
//    }
    public HistoryAdapter(@NonNull DiffUtil.ItemCallback<History> diffCallback){
        super(diffCallback);
    }
//    public HistoryAdapter(String [] place_list){
//        this.place_list = place_list;
//    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryAdapter.HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History current = getItem(position);
        holder.tv_date.setText(current.getDate());
        holder.tv_time.setText(current.getTime());
        holder.tv_location_enter.setText(current.getLocation());
    }


    public static class HistoryDiff extends DiffUtil.ItemCallback<History> {

        @Override
        public boolean areItemsTheSame(@NonNull History oldItem, @NonNull History newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull History oldItem, @NonNull History newItem) {
            return oldItem.getDate().equals(newItem.getDate()) &&
                    oldItem.getLocation().equals(newItem.getLocation());
        }
    }

//    @Override
//    public int getItemCount() {
//        return place_list.length;
//    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_location_enter, tv_date, tv_time;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_location_enter = itemView.findViewById(R.id.tv_location_enter);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_time = itemView.findViewById(R.id.tv_time);
            imageView = itemView.findViewById(R.id.iv_location);
        }
    }
}