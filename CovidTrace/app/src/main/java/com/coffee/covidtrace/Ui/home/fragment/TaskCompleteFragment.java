package com.coffee.covidtrace.Ui.home.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.coffee.covidtrace.Adapter.ThingsAdapter;
import com.coffee.covidtrace.Data.HealthAssessment;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.healthAssessment.QuestionActivity;
import com.coffee.covidtrace.ViewModel.SharedViewModel;

import java.util.Objects;

public class TaskCompleteFragment extends Fragment {

    private TaskCompleteViewModel mViewModel;
    Button btn_instruction;
    private SharedViewModel sharedViewModel;

    String[] contents = {"Changed Location?", "After Vaccination", "How you feeling?"};

    public static TaskCompleteFragment newInstance() {
        return new TaskCompleteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_complete_fragment, container, false);
//        RecyclerView recyclerView = view.findViewById(R.id.th_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recyclerView.setAdapter(new ThingsAdapter(contents));

        return view;    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TaskCompleteViewModel.class);
        // TODO: Use the ViewModel
        btn_instruction = view.findViewById(R.id.btn_instruction);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        btn_instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuestionActivity.class);
                if (sharedViewModel.getCurrent_user().getValue()!=null){
                    UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();
                    Log.d("shared view model, fragment", userEntity.getName());
                    intent.putExtra("user", userEntity);
                    startActivity(intent);
                }
            }
        });
    }

    //get the height based on the content
    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(getView()).requestLayout();
    }

}