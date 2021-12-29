package com.coffee.covidtrace.Ui;

import androidx.appcompat.app.AppCompatActivity;
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

import com.coffee.covidtrace.Adapter.HistoryAdapter;
import com.coffee.covidtrace.Adapter.ReportAdapter;
import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.ReportCase;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_case_main);
        gridView = findViewById(R.id.activity_main_grid_view);
        mViewModel = new ViewModelProvider(this)
                .get(ReportCaseViewModel.class);

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
        mViewModel.getAllCases(1).observe(this, new Observer<List<ReportCase>>() {
            @Override
            public void onChanged(List<ReportCase> reportCasesList) {
                adapter.submitList(reportCasesList);
            }
        });
    }


    public void addNewMemory(View view) {
        Intent intent = new Intent(this, ReportCaseActivity.class);
        startActivity(intent);
    }
}