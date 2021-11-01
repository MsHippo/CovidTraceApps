package com.coffee.covidtrace.bottomNavigation;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.coffee.covidtrace.MainActivity;
import com.coffee.covidtrace.R;


public class HomeFragment extends Fragment {

    private MainActivity mainActivity;
    private Toolbar toolbar;

    public HomeFragment(){
        // require a empty public constructor

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity)activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        return v;
    }
}