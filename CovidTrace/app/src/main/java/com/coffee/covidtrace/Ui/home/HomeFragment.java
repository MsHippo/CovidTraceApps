package com.coffee.covidtrace.Ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffee.covidtrace.Adapter.HomeSelectionPageAdapter;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    HomeSelectionPageAdapter homeSelectionPageAdapter;
    View homeFragement;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    UserEntity userEntity;
    TextView tx_hello_user;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeFragement = inflater.inflate(R.layout.home_fragment, container, false);
        return homeFragement;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // TODO: Use the ViewModel
        //set the title of the app bar
        //Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(R.string.home_title);

        viewPager2 = view.findViewById(R.id.view_pager);
        tx_hello_user = view.findViewById(R.id.tx_hello_user);

        viewPager2.setAdapter(new HomeSelectionPageAdapter(requireActivity()));

        tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //set tab names
                tab.setText(((HomeSelectionPageAdapter)(Objects.requireNonNull(viewPager2.getAdapter()))).titles[position]);
            }
        }).attach();

        assert getArguments() != null;
        tx_hello_user.setText("Hello, " + getArguments().getString("current user"));
    }

}