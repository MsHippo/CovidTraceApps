package com.coffee.covidtrace.Ui.notification;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.coffee.covidtrace.Adapter.HistoryAdapter;
import com.coffee.covidtrace.Adapter.notifi_adapter;
import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.NotificationEntity;
import com.coffee.covidtrace.Data.UserDao;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.history.HistoryViewModel;
import com.coffee.covidtrace.Ui.profile.ProfileViewModel;
import com.coffee.covidtrace.ViewModel.SharedViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private NotificationViewModel notificationViewModel;
    private SharedViewModel sharedViewModel;

    NotificationEntity notificationEntity;
    final notifi_adapter adapter = new notifi_adapter(new notifi_adapter.NotificationDiff());

    private final LinkedList<NotificationEntity> NotificationList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);

        // toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Notification");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.notification_recycleview);
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        int NotificationListSize = NotificationList.size();

        NotificationList.addLast(notificationEntity);
        recyclerView.getAdapter().notifyDataSetChanged();

        recyclerView.smoothScrollToPosition(NotificationListSize);


    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        notificationViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NotificationViewModel.class);
        // TODO: Use the ViewModel


        if (sharedViewModel.getCurrent_user().getValue() != null) {
            UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();

            // Populate the recyclerview with list from database
            notificationViewModel.getAllNotification(userEntity.getId()).observe(this, new Observer<List<NotificationEntity>>() {
                @Override
                public void onChanged(List<NotificationEntity> NotificationList) {
                    adapter.submitList(NotificationList);
                }
            });
        }
    }
}


