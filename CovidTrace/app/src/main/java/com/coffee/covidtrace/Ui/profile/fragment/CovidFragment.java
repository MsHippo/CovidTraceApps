package com.coffee.covidtrace.Ui.profile.fragment;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.coffee.covidtrace.Data.History;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.Ui.home.fragment.TaskCompleteFragment;
import com.coffee.covidtrace.Ui.home.fragment.TaskCompleteViewModel;
import com.coffee.covidtrace.Ui.record.RecordViewModel;
import com.coffee.covidtrace.ViewModel.SharedViewModel;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.LinkedList;
import java.util.List;

public class CovidFragment extends Fragment {
    private CovidViewModel mViewModel;
    View CovidFragment;
    private SharedViewModel sharedViewModel;
    TextView tv_risk_status, tv_vaccination;
    Bundle bundle = new Bundle();
    Integer status, vaccine;
    CardView cv_risk_status_outer, cv_vaccination_outer;

    public static CovidFragment newInstance() {
        return new CovidFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        CovidFragment =  inflater.inflate(R.layout.covid_related_fragment, container, false);

        // Initialize the viewmodel
        mViewModel = new ViewModelProvider(this).get(CovidViewModel.class);

        //make the card view click to have scanning function
        cv_risk_status_outer = CovidFragment.findViewById(R.id.pcv_risk_status_outer);
        cv_vaccination_outer = CovidFragment.findViewById(R.id.pcv_vaccination_outer);
        tv_risk_status = CovidFragment.findViewById(R.id.ptv_risk_status);
        tv_vaccination = CovidFragment.findViewById(R.id.ptv_vaccination);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        if (sharedViewModel.getCurrent_user().getValue()!=null) {
            UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();

//            Log.d(TAG, "onChanged: " + history.getLocation());

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
        return CovidFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CovidViewModel.class);
        // TODO: Use the ViewModel
    }

}
