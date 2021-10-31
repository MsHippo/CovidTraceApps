package com.coffee.covidtrace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.coffee.covidtrace.bottomNavigation.HistoryFragment;
import com.coffee.covidtrace.bottomNavigation.HomeFragment;
import com.coffee.covidtrace.bottomNavigation.ProfileFragment;
import com.coffee.covidtrace.bottomNavigation.RecordFragment;
import com.coffee.covidtrace.bottomNavigation.StatisticFragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBarMenu = findViewById(R.id.bottomNavigationView);
        navigationBarMenu.setSelectedItemId(R.id.homeFragment);

        navigationBarMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            HomeFragment homeFragment = new HomeFragment();
            StatisticFragment statisticFragment = new StatisticFragment();
            RecordFragment recordFragment = new RecordFragment();
            HistoryFragment historyFragment = new HistoryFragment();
            ProfileFragment profileFragment = new ProfileFragment();

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeFragment:
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.fragmentContainerView, homeFragment).commit();
                        return true;
                    case R.id.statisticFragment:
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.fragmentContainerView, statisticFragment
                        ).commit();
                        return true;
                    case R.id.recordFragment:
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.fragmentContainerView, recordFragment
                        ).commit();
                        return true;
                    case R.id.historyFragment:
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.fragmentContainerView, historyFragment
                        ).commit();
                        return true;
                    case R.id.profileFragment:
                        getSupportFragmentManager().beginTransaction().replace(
                                R.id.fragmentContainerView, profileFragment
                        ).commit();
                        return true;
                }
                return false;
            }
        });
    }


}