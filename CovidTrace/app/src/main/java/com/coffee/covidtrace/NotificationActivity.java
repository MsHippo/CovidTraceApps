package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coffee.covidtrace.Adapter.faq_name_adapter;
import com.coffee.covidtrace.Adapter.notifi_adapter;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<notifications> notifiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.notification_recycle);

        initData();
        setRecyclerView();
    }

    private void setRecyclerView(){
        notifi_adapter notifi_adapter = new notifi_adapter(notifiList);
        recyclerView.setAdapter(notifi_adapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        notifiList = new ArrayList<>();

        notifiList.add(new notifications("CPRC KKM", "Talian Hotline...."));
        notifiList.add(new notifications("CPRC KKM", "Talian Hotline...."));
        notifiList.add(new notifications("CPRC KKM", "Talian Hotline...."));
    }
}