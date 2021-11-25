package com.coffee.covidtrace.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.coffee.covidtrace.Ui.home.fragment.ImpNotesFragment;
import com.coffee.covidtrace.Ui.home.fragment.TaskCompleteFragment;
import com.coffee.covidtrace.Ui.profile.fragment.CovidFragment;
import com.coffee.covidtrace.Ui.profile.fragment.PersonalDetailFragment;

public class ProfileSelectionPageAdapter extends FragmentStateAdapter {

    //Determine the name that appear on the tab
    public String[] titles = new String[]{"Personal Detail", "Covid-19 related"};

    public ProfileSelectionPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new PersonalDetailFragment();
            case 1:
                return new CovidFragment();
        }
        return new PersonalDetailFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}

