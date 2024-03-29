package com.coffee.covidtrace.Ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.coffee.covidtrace.Adapter.HistoryAdapter;
import com.coffee.covidtrace.Adapter.ReportAdapter;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ReportCase;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.history.HistoryViewModel;

import java.util.LinkedList;
import java.util.List;

public class ReportCaseMainActivity extends AppCompatActivity {

    private RecyclerView gridView;
    private final LinkedList<ReportCase> reportList = new LinkedList<>();
    ReportCase reportCase;
    final ReportAdapter adapter = new ReportAdapter(new ReportAdapter.ReportDiff());
    ReportCaseViewModel mViewModel;
    TextView activity_main_empty_view;
    UserEntity currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_case_main);
        gridView = findViewById(R.id.activity_main_grid_view);
        activity_main_empty_view = findViewById(R.id.activity_main_empty_view);
        mViewModel = new ViewModelProvider(this)
                .get(ReportCaseViewModel.class);

        currentUser = (UserEntity) getIntent().getSerializableExtra("user");

        // toolbar is defined in the layout file
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Report Rule-Breaking Case");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
//        ab.setDisplayHomeAsUpEnabled(true);

        //make the recycler view in grid layout
        gridView.setLayoutManager(new GridLayoutManager(this, 2));
        gridView.setAdapter(adapter);
//        gridView.setEmptyView(findViewById(R.id.activity_main_empty_view));
        // Store the scanner text to array list
        int reportListSize = reportList.size();
        // Add a new word to the wordList.

        reportList.addLast(reportCase);
        gridView.getAdapter().notifyDataSetChanged();
        // Scroll to the bottom.
        gridView.smoothScrollToPosition(reportListSize);

        // Populate the recyclerview with list from database
        mViewModel.getAllCases(currentUser.getId()).observe(this, new Observer<List<ReportCase>>() {
            @Override
            public void onChanged(List<ReportCase> reportCasesList) {
                if (reportCasesList.isEmpty()){
                    gridView.setVisibility(View.GONE);
                    activity_main_empty_view.setVisibility(View.VISIBLE);
                }
                else{
                        gridView.setVisibility(View.VISIBLE);
                        activity_main_empty_view.setVisibility(View.GONE);
                        adapter.submitList(reportCasesList);
                }

            }
        });
    }


    public void addNewReport(View view) {
        Intent intent = new Intent(this, ReportCaseActivity.class);
        intent.putExtra("user", currentUser);
        startActivity(intent);
    }
}