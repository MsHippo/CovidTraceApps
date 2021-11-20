package com.coffee.covidtrace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.coffee.covidtrace.Ui.vaccination.VaccinationActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.coffee.covidtrace.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationBarMenu;
    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment,
                R.id.statisticFragment,
                R.id.recordFragment,
                R.id.historyFragment,
                R.id.profileFragment)
                .setOpenableLayout(drawer)
                .build();

        NavController sideNavController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, sideNavController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, sideNavController);

        navigationBarMenu = findViewById(R.id.bottomNavigationView);
        navigationBarMenu.setSelectedItemId(R.id.homeFragment);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder
//                (       R.id.homeFragment,
//                        R.id.statisticFragment,
//                        R.id.recordFragment,
//                        R.id.historyFragment,
//                        R.id.profileFragment
//                ).build();


        //bottom navigation
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationBarMenu, navController);

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.bottom_menu, menu);
//        MenuCompat.setGroupDividerEnabled(menu, true);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        NavController navController = Navigation.findNavController(this, R.id.fragment);
//
//        // Handle item selection
//        switch (item.getItemId()) {
//            case R.id.homeFragment:
//                navController.navigate(R.id.homeFragment);
//                return true;
//            case R.id.statisticFragment:
//                navController.navigate(R.id.statisticFragment);
//                return true;
//            case R.id.recordFragment:
//                navController.navigate(R.id.recordFragment);
//                return true;
//            case R.id.historyFragment:
//                navController.navigate(R.id.historyFragment);
//                return true;
//            case R.id.profileFragment:
//                navController.navigate(R.id.profileFragment);
//                return true;
//            case android.R.id.home:
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @SuppressLint("NonConstantResourceId")
    public void main_features(View view) {
        switch (view.getId()){
            case R.id.covid_vaccination:
                Intent intent = new Intent(this, VaccinationActivity.class);
                startActivity(intent);
        }
    }
}