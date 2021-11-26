package com.coffee.covidtrace.Ui.dashboard.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.coffee.covidtrace.R;

public class MalaysiaFragment extends Fragment {
    private MalaysiaViewModel mViewModel;

    public static MalaysiaFragment newInstance() {
        return new MalaysiaFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.malaysia_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MalaysiaViewModel.class);
        // TODO: Use the ViewModel
    }

}