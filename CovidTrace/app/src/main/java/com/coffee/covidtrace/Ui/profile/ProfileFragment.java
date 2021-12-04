package com.coffee.covidtrace.Ui.profile;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffee.covidtrace.Adapter.ProfileSelectionPageAdapter;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.w3c.dom.Text;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    ProfileSelectionPageAdapter profileSelectionPageAdapter;
    View profileFragement;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    TextView txt_user_name;
    UserEntity userEntity;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        profileFragement = inflater.inflate(R.layout.fragment_profile, container, false);
        return  profileFragement;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
        //set the title of the app bar
        //Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(R.string.profile_title);

        viewPager2 = view.findViewById(R.id.profile_viewpager);
        txt_user_name = view.findViewById(R.id.tx_user_name);

        viewPager2.setAdapter(new ProfileSelectionPageAdapter(requireActivity()));


        tabLayout = view.findViewById(R.id.profile_tablayout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //set tab names
                tab.setText(((ProfileSelectionPageAdapter) (Objects.requireNonNull(viewPager2.getAdapter()))).titles[position]);
            }
        }).attach();

        txt_user_name.setText(userEntity.getName());

    }

}