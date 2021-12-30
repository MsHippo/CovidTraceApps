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
import com.coffee.covidtrace.Data.NotificationEntity;
import com.coffee.covidtrace.R;

import org.w3c.dom.Text;

public class notifi_adapter extends ListAdapter<NotificationEntity, notifi_adapter.NotificationViewHolder> {

    public notifi_adapter(@NonNull DiffUtil.ItemCallback<NotificationEntity> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notifi_adapter.NotificationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.notification, parent, false));
    }

    public static class NotificationDiff extends DiffUtil.ItemCallback<NotificationEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull NotificationEntity oldItem, @NonNull NotificationEntity newItem) {
            return true;
        }

        @Override
        public boolean areContentsTheSame(@NonNull NotificationEntity oldItem, @NonNull NotificationEntity newItem) {
            return true;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationEntity current = getItem(position);
        holder.tv_date.setText(current.getDate());
        holder.tv_time.setText(current.getTime());
        holder.tv_detail.setText(current.getDetail());
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView tv_detail, tv_date, tv_time;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_detail = itemView.findViewById(R.id.tv_noti);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}