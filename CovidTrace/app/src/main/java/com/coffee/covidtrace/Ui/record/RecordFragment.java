package com.coffee.covidtrace.Ui.record;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.ViewModel.SharedViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class RecordFragment extends Fragment {

    public RecordViewModel mViewModel;
    public CardView cv_scan;
    View recordFragment;
    private SharedViewModel sharedViewModel;
    TextView user_name, user_ic_passport, tv_place_name, tv_date, tv_time, tv_risk_status, tv_vaccination;
    Bundle bundle = new Bundle();
    History history;
    private final LinkedList<History> lastestHistory = new LinkedList<>();
    Integer status, vaccine;
    CardView cv_risk_status_outer, cv_vaccination_outer, cv_history;

    private final ActivityResultLauncher<ScanOptions> fragmentLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(getContext(), "Cancelled from fragment", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Scanned from fragment: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });

    public static RecordFragment newInstance() {
        return new RecordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        recordFragment = inflater.inflate(R.layout.record_fragment, container, false);

        // Initialize the viewmodel
        mViewModel = new ViewModelProvider(this).get(RecordViewModel.class);

        //make the card view click to have scanning function
        cv_scan= recordFragment.findViewById(R.id.btn_user_check_in);
        user_name = recordFragment.findViewById(R.id.user_name);
        user_ic_passport = recordFragment.findViewById(R.id.user_ic_passport);
        tv_place_name = recordFragment.findViewById(R.id.tv_place_name);
        tv_date = recordFragment.findViewById(R.id.tv_date);
        tv_time = recordFragment.findViewById(R.id.tv_time);
        cv_risk_status_outer = recordFragment.findViewById(R.id.cv_risk_status_outer);
        cv_vaccination_outer = recordFragment.findViewById(R.id.cv_vaccination_outer);
        tv_risk_status = recordFragment.findViewById(R.id.tv_risk_status);
        tv_vaccination = recordFragment.findViewById(R.id.tv_vaccination);
        cv_history = recordFragment.findViewById(R.id.cv_history);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        if (sharedViewModel.getCurrent_user().getValue()!=null) {
            UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();

            user_name.setText(userEntity.getName());
            user_ic_passport.setText(userEntity.getNric());

//            Log.d(TAG, "onChanged: " + history.getLocation());


                //get the latest result
                mViewModel.getLatestHistory(userEntity.getId()).observe(this, new Observer<List<History>>() {
                    @Override
                    public void onChanged(List<History> historyList) {

                        //make sure that the history list is not empty
                        if(!historyList.isEmpty()){
                            history = historyList.get(0);
                            Log.d(TAG, "onChanged: " + history.getLocation());

                            tv_place_name.setText(history.getLocation());
                            tv_date.setText(history.getDate());
                            tv_time.setText(history.getTime());

                            cv_history.setVisibility(View.VISIBLE);
                        }
                    }
                });

                //get live data of the user risk status
                mViewModel.getUserStatus(userEntity.getId()).observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        status = integer;
                        Log.d(TAG, "onCreateView: status " + status);
                        if (status == 0){
                            cv_risk_status_outer.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkGrey));

                            tv_risk_status.setText(R.string.unknown_status);
                            tv_risk_status.setTextColor(ContextCompat.getColor(getContext(), R.color.darkGrey));

                        } else if (status == 1){
                            cv_risk_status_outer.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.skyBlue));

                            tv_risk_status.setText(R.string.no_symptom_low_risk);
                            tv_risk_status.setTextColor(ContextCompat.getColor(getContext(), R.color.skyBlue));

                        } else if (status == 2){
                            cv_risk_status_outer.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkYellow));

                            tv_risk_status.setText(R.string.medium_symptom);
                            tv_risk_status.setTextColor(ContextCompat.getColor(getContext(), R.color.darkYellow));

                        }else{
                            cv_risk_status_outer.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.red177));

                            tv_risk_status.setText(R.string.high_symptom);
                            tv_risk_status.setTextColor(ContextCompat.getColor(getContext(), R.color.red177));

                        }
                    }
                });

                mViewModel.getUserVaccine(userEntity.getId()).observe(this, new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        vaccine = integer;
                        Log.d(TAG, "onCreateView: vaccine " + vaccine);
                        if (vaccine == 0){
                            cv_vaccination_outer.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.red177));

                            tv_vaccination.setText(R.string.vaccination_status0);
                            tv_vaccination.setTextColor(ContextCompat.getColor(getContext(), R.color.red177));

                        } else if (vaccine == 1){
                            cv_vaccination_outer.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.darkYellow));

                            tv_vaccination.setText(R.string.halfly_vaccinated);
                            tv_vaccination.setTextColor(ContextCompat.getColor(getContext(), R.color.darkYellow));

                        } else if (vaccine == 2){
                            cv_vaccination_outer.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.grassGreen));

                            tv_vaccination.setText(R.string.fully_vaccinated);
                            tv_vaccination.setTextColor(ContextCompat.getColor(getContext(), R.color.grassGreen));

                        }
                    }
                });


        }

        lastestHistory.addLast(history);

//        cv_scan.setOnClickListener(v -> scanCustomScanner(recordFragment));
        cv_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = IntentIntegrator.forSupportFragment(RecordFragment.this);
                //set the properties of the scan
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.setOrientationLocked(true);
                integrator.initiateScan();
//                scanCustomScanner(view);

            }
        });




        return recordFragment;
    }

//    public void scanFromFragment() {
//        fragmentLauncher.launch(new ScanOptions());
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(RecordViewModel.class);
        // TODO: Use the ViewModel
        //set the title of the app bar
        //Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(R.string.record_title);
//        new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result != null) {
//                            if (result.getData() == null) {
//                                Log.e("Scan*******", "Cancelled scan");
//                            } else {
//                                Toast.makeText(getContext(), "Scanned Successfully", Toast.LENGTH_LONG).show();
//                                Log.e("Scan", "Scanned");
////                textViewScanResult.setText(result.getContents());
//
//                                // Database insert and update the recyclerview
//                                // Scanned location name from QR code
//                                String scan_location = result.getResultCode();
//                                String current_date = java.time.LocalDate.now().toString();
//
//                                History history = new History(scan_location, current_date);
//                                mViewModel.insert(history);
//
//                                Intent intent = new Intent(getActivity(), SuccessCheckInActivity.class);
//                                if (history != null) {
//                                    intent.putExtra("SCAN_RESULTS", (Parcelable) history);
//                                }
//
//                                startActivity(intent);
//                            }
//
//
//                        }
//                    }
//                };
//    }

    public void scanCustomScanner(View view) {
        ScanOptions options = new ScanOptions().setOrientationLocked(false).setCaptureActivity(CustomScanActivity.class);
        fragmentLauncher.launch(options);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if (result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");
            } else {
                Toast.makeText(getContext(), "Scanned Successfully", Toast.LENGTH_LONG).show();
                Log.e("Scan", "Scanned");
//                textViewScanResult.setText(result.getContents());

                // Database insert and update the recyclerview
                // Scanned location name from QR code
                String scan_location = result.getContents();

                //formatting the date and time
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String current_date_time = now.format(format);

                //formatting the date and time
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate dateNow = LocalDate.now();
                String current_date = dateNow.format(dateFormat);


                //formatting the date and time
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime timeNow = LocalTime.now();
                String current_time = timeNow.format(timeFormat);


                sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

                if (sharedViewModel.getCurrent_user().getValue()!=null){
                    UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();
                    Log.d("shared view model, Record fragment", userEntity.getName());

                    int user_id = userEntity.getId();

                    History history = new History(scan_location, current_date, user_id, current_time);
                    mViewModel.insert(history);

                    Intent intent = new Intent(getActivity(), SuccessCheckInActivity.class);
                    intent.putExtra("user", userEntity);

                    if (history!=null){
                        intent.putExtra("SCAN_RESULTS", history);
                    }

                    startActivity(intent);
                }


            }


        }
    }

}