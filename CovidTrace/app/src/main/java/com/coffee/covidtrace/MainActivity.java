package com.coffee.covidtrace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.Ui.dependency.DependencyAddingActivity;
import com.coffee.covidtrace.Ui.SymptomActivity;
import com.coffee.covidtrace.Ui.healthAssessment.HealthStartActivity;
import com.coffee.covidtrace.Ui.hotspot.HotspotActivity;
import com.coffee.covidtrace.Ui.ReportCaseActivity;
import com.coffee.covidtrace.Ui.notification.NotificationActivity;
import com.coffee.covidtrace.Ui.vaccination.VaccinationActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationBarMenu;
    private AppBarConfiguration appBarConfiguration;
//    private ActivityMainBinding binding;
    Intent intent = getIntent();
    UserEntity userEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

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


        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment);
        assert navHostFragment != null;
        NavController sideNavController = navHostFragment.getNavController();

//        sideNavController = Navigation.findNavController(this, R.id.fragment);
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
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", userEntity);

        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);

//        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
//            @Override
//            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
//                // We use a String here, but any type that can be put in a Bundle is supported
//                String result = bundle.getString("bundleKey");
//                // Do something with the result
//            }
//        });

//        NavController navController = navHostFragment.getNavController();
        NavController navController = navHostFragment.getNavController();
        navController.setGraph(R.navigation.app_nav, getIntent().getExtras());
//        NavController navController = Navigation.findNavController (this, R.id.fragment);
//        navController.navigate(R.id.fragment,bundle);
//        Navigation.findNavController(this, R.id.fragment).navigate(R.id.fragment,bundle);
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
    public void MainFeatures(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_covid_vaccination:
                intent = new Intent(this, VaccinationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_report_case:
                intent = new Intent(this, ReportCaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_hotspot:
                intent = new Intent(this, HotspotActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_user_status:
                intent = new Intent(this, HealthStartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_dependency:
                intent = new Intent(this, DependencyAddingActivity.class);
                startActivity(intent);
                break;
            case R.id.faqs_card:
                intent = new Intent(this, FAQActivity.class);
                startActivity(intent);
                break;
            case R.id.symptoms_card:
                intent = new Intent(this, SymptomActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        NavController navController = Navigation.findNavController(this, R.id.fragment);

        Intent intent;
        switch (item.getItemId()) {
//            case R.id.homeFragment:
//                navController.navigate(R.id.homeFragment);
//                break;
//            case R.id.statisticFragment:
//                navController.navigate(R.id.statisticFragment);
//                break;
//            case R.id.recordFragment:
//                navController.navigate(R.id.recordFragment);
//                break;
//            case R.id.historyFragment:
//                navController.navigate(R.id.historyFragment);
//                break;
//            case R.id.profileFragment:
//                navController.navigate(R.id.profileFragment);
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
}