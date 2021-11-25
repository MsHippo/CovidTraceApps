package com.coffee.covidtrace.Ui.profile.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.home.fragment.TaskCompleteFragment;
import com.coffee.covidtrace.Ui.home.fragment.TaskCompleteViewModel;

public class CovidFragment extends Fragment {
    private CovidViewModel mViewModel;

    public static CovidFragment newInstance() {
        return new CovidFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.covid_related_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CovidViewModel.class);
        // TODO: Use the ViewModel
    }

}
