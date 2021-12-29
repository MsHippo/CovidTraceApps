package com.coffee.covidtrace.Ui.hotspot;

import static android.content.ContentValues.TAG;
import static com.coffee.covidtrace.Constants.MAPVIEW_BUNDLE_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.HotSpot;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.record.SuccessCheckInActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;


public class HotspotActivity extends AppCompatActivity implements OnMapReadyCallback {

    //widget
    private MapView mMapView;
    private SearchView searchView;
    private GoogleMap googleMap;
    private Button btn_auto_focus;
    private FusedLocationProviderClient mlocationClient;
    private TextView txtUserCurrentLocation, tv_case_no, tv_cone_area;
    private TextView txtTitleLocation;
    private CardView cv_cone_area, cv_cases_no;
    private ProgressBar progressBar;
    Location currentLocation;
    private static final int REQUEST_CODE = 101;
    private ResultReceiver resultReceiver;
    UserEntity currentUser;
    private HotspotViewModel hotspotViewModel;
    HotSpot hotSpot;

    private long UPDATE_INTERVAL = 10 * 1000;  /* 10 secs */
    private long FASTEST_INTERVAL = 2000; /* 2 sec */

    @SuppressLint({"VisibleForTests"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot);
        resultReceiver = new AddressResultReceiver(new Handler());

        Toolbar toolbar = findViewById(R.id.toolbar);
        mMapView = findViewById(R.id.map);
        searchView = findViewById(R.id.search_location);
        btn_auto_focus = findViewById(R.id.btn_auto_focus);
        txtUserCurrentLocation = findViewById(R.id.txt_user_current_location);
        txtTitleLocation = findViewById(R.id.txt_title_location);
        progressBar = findViewById(R.id.progress_bar);
        tv_case_no = findViewById(R.id.tv_case_no);
        cv_cone_area = findViewById(R.id.cv_cone_area);
        tv_cone_area = findViewById(R.id.tv_cone_area);
        cv_cases_no = findViewById(R.id.cv_cases_no);

        currentUser = (UserEntity) getIntent().getSerializableExtra("user");
        hotspotViewModel = new ViewModelProvider(this)
                .get(HotspotViewModel.class);

        toolbar.setTitle("COVID-19 Hotspot Tracker");
        setSupportActionBar(toolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        assert ab != null;
//        ab.setDisplayHomeAsUpEnabled(true);

        initGoogleMap(savedInstanceState);

        mlocationClient = LocationServices.getFusedLocationProviderClient(this);

        btn_auto_focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(HotspotActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    getCurrentLoc();
                }
                else
                {
                    //if permission denied
                    //Request Permission
                    ActivityCompat.requestPermissions(HotspotActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

                }

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(HotspotActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 10);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert addressList != null;
                    Address address = addressList.get(0);

                    txtTitleLocation.setVisibility(View.VISIBLE);
                    txtUserCurrentLocation.setVisibility(View.VISIBLE);
                    txtUserCurrentLocation.setText(address.getAddressLine(0));

                    Log.d(TAG, "Search view: latitude, longitude" + address.getLatitude() + " " + address.getLongitude());
                    goToLocation(address.getLatitude(), address.getLongitude(), location);
//                    getCurrentLoc();
//                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//                    googleMap.addMarker(new MarkerOptions()
//                            .position(latLng).title(location)
//                            .draggable(false).visible(true));
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void getCurrentLoc() {

        progressBar.setVisibility(View.VISIBLE);
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    { Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        LocationServices.getFusedLocationProviderClient(HotspotActivity.this)
                .requestLocationUpdates(locationRequest, new LocationCallback(){
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(HotspotActivity.this)
                                .removeLocationUpdates(this);
                        if (locationResult!=null && locationResult.getLocations().size()>0){
                            int latestLocationIndex = locationResult.getLocations().size()-1;

                            //get current latitude and longitude of the user
                            double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                            double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();

                            Location location = new Location("providerNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);
                            fetchAddressFromLatLng(location);
                        }
                        else{
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, Looper.getMainLooper());

        Task<Location> task = mlocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                //when success
                if (location!=null){
                    currentLocation = location;
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude()
                            + " " + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onSuccess: latitude, longitude" + currentLocation.getLatitude() + " " + currentLocation.getLongitude());
                    mMapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {

                            //initialize lan lng
                            goToLocation(location.getLatitude(), location.getLongitude(), String.valueOf(location.getAccuracy()));
                        }
                    });
                }
            }
        });

//        mlocationClient.getLastLocation().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                Location location = task.getResult();
//                goToLocation(location.getLatitude(), location.getLongitude(),location.toString());
////                goToLocation(location.getLatitude(), location.getLongitude());
//            }
//        });
    }

    private void fetchAddressFromLatLng(Location location){
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(Constants.RECEIVER, resultReceiver);
        intent.putExtra(Constants.LOCATION_DATA_EXTRA, location);
        startService(intent);
    }

    private class AddressResultReceiver extends ResultReceiver{
        AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == Constants.SUCCESS_RESULT){
                txtTitleLocation.setVisibility(View.VISIBLE);
                txtUserCurrentLocation.setVisibility(View.VISIBLE);
                txtUserCurrentLocation.setText(resultData.getString(Constants.RESULT_DATA_KEY));
            }else{
                Toast.makeText(
                        HotspotActivity.this,
                        resultData.getString(Constants.RESULT_DATA_KEY),
                        Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }
    }

    private void goToLocation(double latitude, double longitude, String location) {
        googleMap.clear();
//        progressBar.setVisibility(View.VISIBLE);
        Log.d(TAG, "goToLocation: latitude longitude " + latitude + " " + longitude );
        Log.d(TAG, "goToLocation: abs latitude longitude " + Math.round(latitude) + " " + Math.round(longitude) );
        hotspotViewModel.getmAllHotspot(Math.round(latitude), Math.round(longitude)).observe(this, new Observer<List<HotSpot>>() {
            @SuppressLint({"ResourceAsColor", "SetTextI18n"})
            @Override
            public void onChanged(List<HotSpot> hotSpots) {
                cv_cases_no.setVisibility(View.VISIBLE);
                cv_cone_area.setVisibility(View.VISIBLE);
                if (!hotSpots.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    hotSpot = hotSpots.get(0);
                    tv_case_no.setText(getResources().getString(R.string.number_case_warning_front) + " " + hotSpot.getCases() + " " +getResources().getString(R.string.number_case_warning_back));
                    Log.d(TAG, "onChanged: text" + getResources().getString(R.string.number_case_warning_front));
                    if (hotSpot.getCases() >= 15){
                        cv_cone_area.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red177));
                        tv_cone_area.setText(R.string.text_of_red_area);
                    }else if (hotSpot.getCases() >= 10){
                        cv_cone_area.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.darkYellow));
                        tv_cone_area.setText(R.string.text_of_yellow_area);
                    }else if (hotSpot.getCases() < 10){
                        cv_cone_area.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.grassGreen));
                        tv_cone_area.setText(R.string.text_of_green_area);
                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    tv_case_no.setText("No data for the current region. Please come back later. Thanks");
                    cv_cone_area.setCardBackgroundColor(R.color.darkGrey);
                    tv_cone_area.setText(getResources().getString(R.string.text_of_unknown_area));
                }

            }
        });
        LatLng latLng = new LatLng(latitude, longitude);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng).title(location)
                .draggable(false).visible(true);

//        googleMap.addMarker(new MarkerOptions());
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 45));
        googleMap.addMarker(markerOptions);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    private void initGoogleMap(Bundle savedInstanceState){
        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);
    }

//    public void searchHotspotArea(View view) {
//        Intent intent = new Intent(this, HotSpotResultActivity.class);
////        intent.putExtra("LOCATION_RESULTS", String.valueOf(location));
//        startActivity(intent);
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {

        googleMap = map;

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(this, new String[]
////                    { Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
//            return;
//        }

//        map.setMyLocationEnabled(true);


    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLoc();
            }else {
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}