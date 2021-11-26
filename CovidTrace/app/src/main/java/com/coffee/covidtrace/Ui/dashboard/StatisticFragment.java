package com.coffee.covidtrace.Ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffee.covidtrace.Adapter.StatisticSelectionPageAdapter;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.profile.ProfileFragment;
import com.coffee.covidtrace.Ui.profile.ProfileViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class StatisticFragment extends Fragment {

    private StatisticViewModel mViewModel;
    StatisticSelectionPageAdapter statisticsSelectionPageAdapter;
    View statisticFragment;
    TabLayout tabLayout;
    ViewPager2 viewPager2;

    public static StatisticFragment newInstance() {
        return new StatisticFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        statisticFragment = inflater.inflate(R.layout.statistic_fragment, container, false);
        return  statisticFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StatisticViewModel.class);
        // TODO: Use the ViewModel
        //set the title of the app bar
        //Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(R.string.profile_title);

        viewPager2 = view.findViewById(R.id.statistics_viewpager);
        viewPager2.setAdapter(new StatisticSelectionPageAdapter(requireActivity()));

        tabLayout = view.findViewById(R.id.statistics_tablayout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //set tab names
                tab.setText(((StatisticSelectionPageAdapter) (Objects.requireNonNull(viewPager2.getAdapter()))).titles[position]);
            }
        }).attach();

    }

}