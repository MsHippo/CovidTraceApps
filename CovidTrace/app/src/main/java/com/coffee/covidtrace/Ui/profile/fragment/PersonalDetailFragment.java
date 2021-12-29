package com.coffee.covidtrace.Ui.profile.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.ViewModel.SharedViewModel;


public class PersonalDetailFragment extends Fragment {

    private PersonalDetailViewModel mViewModel;
    TextView txt_email, txt_phone, txt_ic;
    UserEntity currentUser;
    private SharedViewModel sharedViewModel;

    public static PersonalDetailFragment newInstance() {
        return new PersonalDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal_detail_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PersonalDetailViewModel.class);
        // TODO: Use the ViewModel

        txt_email = view.findViewById(R.id.profile_email);
        txt_phone = view.findViewById(R.id.profile_phone);
        txt_ic = view.findViewById(R.id.profile_IC);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        if (sharedViewModel.getCurrent_user().getValue()!=null){
            UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();
            Log.d("shared view model, Personal Detail fragment", userEntity.getEmail());
            txt_email.setText(userEntity.getEmail());
            Log.d("shared view model, Personal Detail fragment", userEntity.getPhone());
            txt_phone.setText(userEntity.getPhone());
            Log.d("shared view model, Personal Detail fragment", userEntity.getNric());
            txt_ic.setText(userEntity.getNric());
        }
    }
}
