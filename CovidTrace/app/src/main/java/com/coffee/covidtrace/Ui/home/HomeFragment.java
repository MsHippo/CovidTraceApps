package com.coffee.covidtrace.Ui.home;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffee.covidtrace.Adapter.HomeSelectionPageAdapter;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.ViewModel.SharedViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;
    HomeSelectionPageAdapter homeSelectionPageAdapter;
    View homeFragement;
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    UserEntity currentUser;
    TextView tx_hello_user;
    private SharedViewModel sharedViewModel;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }



    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        homeFragement = inflater.inflate(R.layout.home_fragment, container, false);
        Bundle bundle =  getArguments();
        assert bundle != null;
        currentUser = (UserEntity) bundle.getSerializable("user");
        if (currentUser!=null){
            Log.d("home fragment", currentUser.getName());
        }


//        String name = HomeFragmentArgs.fromBundle(getArguments()).getUser().getName();
        return homeFragement;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        // TODO: Use the ViewModel
        //set the title of the app bar
        //Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setTitle(R.string.home_title);


        viewPager2 = view.findViewById(R.id.view_pager);
        tx_hello_user = view.findViewById(R.id.tx_hello_user);

        viewPager2.setAdapter(new HomeSelectionPageAdapter(requireActivity()));

        tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                //set tab names
                tab.setText(((HomeSelectionPageAdapter)(Objects.requireNonNull(viewPager2.getAdapter()))).titles[position]);
            }
        }).attach();

        Bundle bundle = getArguments();
        assert bundle != null;
        currentUser = (UserEntity) bundle.getSerializable("user");

        if (currentUser!=null){
            Log.d("home fragment", currentUser.getName());
            tx_hello_user.setText("Hello, " + currentUser.getName());
        }

        if (sharedViewModel.getCurrent_user().getValue()!=null){
            UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();
            Log.d("shared view model, fragment", userEntity.getName());
            tx_hello_user.setText("Hello, " + userEntity.getName());
        }

//        mainViewModel.getLogInUser().observe(getViewLifecycleOwner(), item->{
//            tx_hello_user.setText("Hello, " + item.getName());
//        });

//        NavController navController = Navigation.findNavController(view);
////        NavHostFragment.findNavController(this).navigate(action_homeFragment_to_profileFragment3.returnToAWithArguments(text))
//        HomeFragmentDirections.ActionHomeFragmentToProfileFragment3 action = HomeFragmentDirections.actionHomeFragmentToProfileFragment3();
//        navController.navigate(action);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            savedInstanceState = getArguments();
            assert savedInstanceState != null;
            currentUser = (UserEntity) savedInstanceState.getSerializable("user");
            tx_hello_user.setText("Hello, " + currentUser.getName());
        } else {
//            randomGoodDeed = viewModel.generateRandomGoodDeed();
        }
    }
}