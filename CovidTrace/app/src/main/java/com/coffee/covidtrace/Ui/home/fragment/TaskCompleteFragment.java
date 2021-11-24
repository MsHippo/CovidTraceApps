package com.coffee.covidtrace.Ui.home.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffee.covidtrace.Adapter.ThingsAdapter;
import com.coffee.covidtrace.R;

public class TaskCompleteFragment extends Fragment {

    private TaskCompleteViewModel mViewModel;

    String[] contents = {"Changed Location?", "After Vaccination", "How you feeling?"};

    public static TaskCompleteFragment newInstance() {
        return new TaskCompleteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_complete_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.th_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(new ThingsAdapter(contents));
        return view;    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TaskCompleteViewModel.class);
        // TODO: Use the ViewModel
    }

}