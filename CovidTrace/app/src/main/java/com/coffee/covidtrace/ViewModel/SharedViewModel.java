package com.coffee.covidtrace.ViewModel;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.coffee.covidtrace.Data.UserEntity;

import java.io.Serializable;

public class SharedViewModel extends ViewModel {

//    UserEntity user;
//    Bundle bundle = new Bundle();
//    user = (UserEntity) getIntent().getSerializableExtra("user");
//    bundle.putSerializable("user",currentUser);

    private final MutableLiveData<UserEntity> current_user = new MutableLiveData<>();
    private final MutableLiveData<String> email = new MutableLiveData<>();
    private final MutableLiveData<String> password = new MutableLiveData<>();

    private UserEntity user;
    private Context context;


    public void setCurrent_user(UserEntity user){
        current_user.setValue(user);
    }

    public LiveData<UserEntity>getCurrent_user(){
        return current_user;
    }

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


//    public SharedViewModel(UserEntity user, Context context) {
//        this.user = user;
//        this.context = context;
//    }


}
