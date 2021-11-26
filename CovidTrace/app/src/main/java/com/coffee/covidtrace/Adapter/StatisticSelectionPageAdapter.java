package com.coffee.covidtrace.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.coffee.covidtrace.Ui.dashboard.fragment.MalaysiaFragment;
import com.coffee.covidtrace.Ui.dashboard.fragment.WorldFragment;

public class StatisticSelectionPageAdapter extends FragmentStateAdapter {

        //Determine the name that appear on the tab
        public String[] titles = new String[]{"Malaysia", "World"};

        public StatisticSelectionPageAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            switch (position){
                case 0:
                    return new MalaysiaFragment();
                case 1:
                    return new WorldFragment();
            }
            return new MalaysiaFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }

