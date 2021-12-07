package com.coffee.covidtrace.ViewModel;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.coffee.covidtrace.Data.UserEntity;

import java.io.Serializable;

public class MainViewModel extends ViewModel {

//    UserEntity user;
//    Bundle bundle = new Bundle();
//    user = (UserEntity) getIntent().getSerializableExtra("user");
//    bundle.putSerializable("user",currentUser);

    private final MutableLiveData<UserEntity> current_user = new MutableLiveData<>();
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();

    private UserEntity user;
    private Context context;

//    private PlayersRepository repository = new PlayersRepository();

//    public void logInUser(UserEntity currentUser) {
//        current_user.setValue(currentUser);
//    }
//
//    public MutableLiveData<UserEntity> getLogInUser() {
//        return current_user;
//    }
//
//    public UserEntity getUser() {
//        return user;
//    }


    public MainViewModel(UserEntity user, Context context) {
        this.user = user;
        this.context = context;
    }


}
