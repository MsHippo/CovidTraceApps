package com.coffee.covidtrace.Ui.notification;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.coffee.covidtrace.Adapter.faq_name_adapter;
import com.coffee.covidtrace.Adapter.notifi_adapter;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.notifications;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<notifications> notifiList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        // toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notification");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        //Recycler view
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