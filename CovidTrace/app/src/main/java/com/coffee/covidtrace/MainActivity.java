package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationBarMenu = findViewById(R.id.bottomNavigationView);
        navigationBarMenu.setSelectedItemId(R.id.homeFragment);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder
                (
                        R.id.homeFragment,
                        R.id.statisticFragment,
                        R.id.recordFragment,
                        R.id.historyFragment,
                        R.id.profileFragment
                ).build();

        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationBarMenu, navController);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_menu, menu);
        MenuCompat.setGroupDividerEnabled(menu, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        NavController navController = Navigation.findNavController(this, R.id.fragment);

        // Handle item selection
        switch (item.getItemId()) {
            case R.id.homeFragment:
                navController.navigate(R.id.homeFragment);
                return true;
            case R.id.statisticFragment:
                navController.navigate(R.id.statisticFragment);
                return true;
            case R.id.recordFragment:
                navController.navigate(R.id.recordFragment);
                return true;
            case R.id.historyFragment:
                navController.navigate(R.id.historyFragment);
                return true;
            case R.id.profileFragment:
                navController.navigate(R.id.profileFragment);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}