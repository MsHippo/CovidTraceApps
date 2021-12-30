package com.coffee.covidtrace.Adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.coffee.covidtrace.Ui.home.fragment.ImpNotesFragment;
import com.coffee.covidtrace.Ui.home.fragment.TaskCompleteFragment;

public class HomeSelectionPageAdapter extends FragmentStateAdapter {

    //Determine the name that appear on the tab
    public String[] titles = new String[]{"Important Notes", "Task to Complete"};

    public HomeSelectionPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new ImpNotesFragment();
            case 1:
                return new TaskCompleteFragment();
        }
        return new ImpNotesFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


}
