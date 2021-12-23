package com.coffee.covidtrace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.NavType;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.Ui.dashboard.StatisticFragment;
import com.coffee.covidtrace.Ui.dependency.DependencyAddingActivity;
import com.coffee.covidtrace.Ui.SymptomActivity;
import com.coffee.covidtrace.Ui.healthAssessment.HealthStartActivity;
import com.coffee.covidtrace.Ui.history.HistoryFragment;
import com.coffee.covidtrace.Ui.home.HomeFragment;
import com.coffee.covidtrace.Ui.home.HomeFragmentDirections;
import com.coffee.covidtrace.Ui.hotspot.HotspotActivity;
import com.coffee.covidtrace.Ui.ReportCaseActivity;
import com.coffee.covidtrace.Ui.notification.NotificationActivity;
import com.coffee.covidtrace.Ui.profile.ProfileFragment;
import com.coffee.covidtrace.Ui.record.RecordFragment;
import com.coffee.covidtrace.Ui.vaccination.VaccinationActivity;
import com.coffee.covidtrace.ViewModel.SharedViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.Objects;


public class MainActivity extends AppCompatActivity{

    BottomNavigationView navigationBarMenu;
    private AppBarConfiguration appBarConfiguration;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavArgument userArg;

    DrawerLayout drawer;
    NavigationView navigationView;

//    private ActivityMainBinding binding;
    UserEntity currentUser;
    Bundle bundle = new Bundle();
    NavController sideNavController;
    private SharedViewModel sharedViewModel;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationBarMenu = findViewById(R.id.bottomNavigationView);


        //view model
        sharedViewModel = new ViewModelProvider(this)
                .get(SharedViewModel.class);

        //new method
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.start, R.string.close);
//        drawer.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//        navigationView.setNavigationItemSelectedListener(this);


        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment,
                R.id.statisticFragment,
                R.id.recordFragment,
                R.id.historyFragment,
                R.id.profileFragment)
                .setOpenableLayout(drawer)
                .build();

//        NavHostFragment navHostFragment = NavHostFragment.create(R.navigation.app_nav);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, navHostFragment)
//                .setPrimaryNavigationFragment(navHostFragment).commit();

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment);
//
        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        bundle.putSerializable("user",currentUser);
        sharedViewModel.setCurrent_user(currentUser);
//
//        assert navHostFragment != null;
//        NavController sideNavController = navHostFragment.getNavController();
/////////////////////////////
//        sideNavController = Navigation.findNavController(this, R.id.fragment);
// ///////////////////////////
        assert navHostFragment != null;
        navHostFragment.setArguments(bundle);
        sideNavController = navHostFragment.getNavController();
        sideNavController.setGraph(R.navigation.app_nav, bundle);
// ///////////////////////////

//        userArg = new NavArgument.Builder().setDefaultValue(currentUser).build();
//
//        sideNavController = Navigation.findNavController(this, R.id.fragment);
//        NavInflater navInflater = sideNavController.getNavInflater();
//        NavGraph navGraph = navInflater.inflate(R.navigation.app_nav);
//        navGraph.addArgument("user", userArg);
//        sideNavController.setGraph(navGraph);

        NavigationUI.setupActionBarWithNavController(this, sideNavController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, sideNavController);
//
//        navigationBarMenu.setOnItemSelectedListener(
//                item -> {
//                    onNavigationItemSelected(item);
//                    return false;
//                }
//
//        );

        //////////////////////////////////

//        navigationBarMenu.setSelectedItemId(R.id.homeFragment);

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
//        NavController navController = navHostFragment.getNavController();
//        NavController navController = Navigation.findNavController(this, R.id.fragment);
//        navController.setGraph(R.navigation.app_nav, bundle);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        ///////////////////////////////
        NavigationUI.setupWithNavController(navigationBarMenu, sideNavController);


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

    @SuppressLint("ResourceType")
    @Override
    public boolean onSupportNavigateUp() {
//        onBackPressed();
        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        bundle.putSerializable("user",currentUser);
//        sideNavController = Navigation.findNavController(this, R.id.fragment);
//        sideNavController.setGraph(R.navigation.app_nav, bundle);
//        NavController navController = Navigation.findNavController(this, R.id.fragment);
//        navController.setGraph(R.navigation.app_nav, bundle);
//        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
//        bundle.putSerializable("user",currentUser);
        //////////////////////////////////
//        NavHostFragment navHostFragment =
//                (NavHostFragment) getSupportFragmentManager()
//                        .findFragmentById(R.id.fragment);
//
//        assert navHostFragment != null;
//        navHostFragment.setArguments(bundle);
//        sideNavController = navHostFragment.getNavController();
        //////////////////////////////////
//        NavController navController = navHostFragment.getNavController();
//        userArg = new NavArgument.Builder().setDefaultValue(currentUser).build();
//
//        sideNavController = Navigation.findNavController(this, R.id.fragment);
//        NavInflater navInflater = sideNavController.getNavInflater();
//        NavGraph navGraph = navInflater.inflate(R.navigation.app_nav);
//        navGraph.addArgument("user", userArg);
//        sideNavController.setGraph(navGraph);

//        navController.setGraph(R.navigation.app_nav, bundle);

        sharedViewModel.setCurrent_user(currentUser);
        sideNavController = Navigation.findNavController(this, R.id.fragment);
//        sideNavController = Navigation.findNavController(navigationView)
//                .setGraph(R.navigation.app_nav, intent.extras);
        return NavigationUI.navigateUp(sideNavController, appBarConfiguration)
                || onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        bundle.putSerializable("user",currentUser);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment);

        assert navHostFragment != null;
        navHostFragment.setArguments(bundle);
        sideNavController = navHostFragment.getNavController();
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }


        @SuppressLint("NonConstantResourceId")
        public void MainFeatures(View view) {
        Intent intent = getIntent();
        switch (view.getId()) {
            case R.id.btn_covid_vaccination:
                intent = new Intent(this, VaccinationActivity.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
                break;
            case R.id.btn_report_case:
                intent = new Intent(this, ReportCaseActivity.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
                break;
            case R.id.btn_hotspot:
                intent = new Intent(this, HotspotActivity.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
                break;
            case R.id.btn_user_status:
                intent = new Intent(this, HealthStartActivity.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
                break;
            case R.id.btn_dependency:
                intent = new Intent(this, DependencyAddingActivity.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
                break;
            case R.id.faqs_card:
                intent = new Intent(this, FAQActivity.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
                break;
            case R.id.symptoms_card:
                intent = new Intent(this, SymptomActivity.class);
                intent.putExtra("user", currentUser);
                startActivity(intent);
                break;
            default:
                break;
        }
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        NavController navController = Navigation.findNavController(this, R.id.fragment);
//
//        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
//        bundle.putSerializable("user",currentUser);
//
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)){
//            return true;
//        }
        Intent intent;
        switch (item.getItemId()) {
//            case R.id.homeFragment:
//                navController.navigate(R.id.homeFragment, bundle);
//                break;
//            case R.id.statisticFragment:
//                navController.navigate(R.id.statisticFragment, bundle);
//                break;
//            case R.id.recordFragment:
//                navController.navigate(R.id.recordFragment, bundle);
//                break;
//            case R.id.historyFragment:
//                navController.navigate(R.id.historyFragment, bundle);
//                break;
//            case R.id.profileFragment:
//                navController.navigate(R.id.profileFragment, bundle);
//                break;
//            case android.R.id.home:
//                this.finish();
//                break;
            case R.id.refresh:
                Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.notifications:
//                Toast.makeText(this, "Notifications Clicked", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        Intent intent;
//        Fragment selectedFragment = null;
//        switch (item.getItemId()) {
//            case R.id.homeFragment:
//                selectedFragment = new HomeFragment();
//                break;
//            case R.id.statisticFragment:
//                selectedFragment = new StatisticFragment();
//                break;
//            case R.id.recordFragment:
//                selectedFragment = new RecordFragment();
//                break;
//            case R.id.historyFragment:
//                selectedFragment = new HistoryFragment();
//                break;
//            case R.id.profileFragment:
//                selectedFragment = new ProfileFragment();
//                break;
////            case android.R.id.home:
////                this.finish();
////                break;
//            case R.id.refresh:
//                Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.notifications:
////                Toast.makeText(this, "Notifications Clicked", Toast.LENGTH_SHORT).show();
//                intent = new Intent(this, NotificationActivity.class);
//                startActivity(intent);
//                break;
//        }
//        assert selectedFragment != null;
//        selectedFragment.setArguments(bundle);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();
//        return true;
//    }
}