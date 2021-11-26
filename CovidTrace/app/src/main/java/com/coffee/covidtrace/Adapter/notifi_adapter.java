package com.coffee.covidtrace.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.coffee.covidtrace.NotificationActivity;
import com.coffee.covidtrace.NotificationDetailActivity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.faqs;
import com.coffee.covidtrace.notifications;

import java.util.List;

public class notifi_adapter extends RecyclerView.Adapter<notifi_adapter.notifiVH> {

    List<notifications> notifiList;

    public notifi_adapter(List<notifications> notifiList) {
        this.notifiList = notifiList;
    }

    @NonNull
    @Override
    public notifi_adapter.notifiVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification, parent, false);
        return new notifi_adapter.notifiVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notifiVH holder, int position) {
        notifications notifications = notifiList.get(position);
        holder.notifi_names.setText(notifications.getnotifi_name());
        holder.notifi_details.setText(notifications.getnotifi_detail());
    }

    @Override
    public int getItemCount() {
        return notifiList.size();
    }

    public class notifiVH extends RecyclerView.ViewHolder {

        TextView notifi_names, notifi_details;
        LinearLayout linearLayout;
        RelativeLayout relativeLayout;

        public notifiVH(@NonNull View itemView) {
            super(itemView);

            notifi_names = itemView.findViewById(R.id.notifi_name);
            notifi_details = itemView.findViewById(R.id.notifi_description);

            linearLayout = itemView.findViewById(R.id.notifilinear);
            relativeLayout = itemView.findViewById(R.id.notifirelative);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(view.getContext(), NotificationDetailActivity.class);
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
