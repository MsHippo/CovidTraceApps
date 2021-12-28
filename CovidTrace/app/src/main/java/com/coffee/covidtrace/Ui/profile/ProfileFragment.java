package com.coffee.covidtrace.Ui.profile;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavArgument;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffee.covidtrace.Adapter.ProfileSelectionPageAdapter;
import com.coffee.covidtrace.Data.UserEntity;
import com.coffee.covidtrace.R;
import com.coffee.covidtrace.ViewModel.SharedViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
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
    UserEntity currentUser;
    private SharedViewModel sharedViewModel;


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


        Bundle bundle = getArguments();
        assert bundle != null;
//        currentUser = ProfileFragmentArgs.fromBundle(getArguments()).getUser();
        currentUser = (UserEntity) bundle.getSerializable("user");

        if (currentUser!=null){
            Log.d("Profile fragment", String.valueOf(currentUser));
        }

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        if (sharedViewModel.getCurrent_user().getValue()!=null){
            UserEntity userEntity = sharedViewModel.getCurrent_user().getValue();
            Log.d("shared view model, Profile fragment", userEntity.getName());
            txt_user_name.setText(userEntity.getName());
        }

//        if (getArguments()!=null){
//            ProfileFragmentArgs profileFragmentArgs = ProfileFragmentArgs.fromBundle(getArguments());
//            currentUser = profileFragmentArgs.getUser();
//            Log.d("Profile fragment", String.valueOf(currentUser));
//        }
//        userArg = new NavArgument.Builder().setDefaultValue(currentUser).build();

//        sideNavController = Navigation.findNavController(this, R.id.fragment);
//        NavInflater navInflater = sideNavController.getNavInflater();
//        NavGraph navGraph = navInflater.inflate(R.navigation.app_nav);
//        navGraph.addArgument("user", userArg);
//        sideNavController.setGraph(navGraph);


    }

}