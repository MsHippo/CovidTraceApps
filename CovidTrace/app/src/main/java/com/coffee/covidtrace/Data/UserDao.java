package com.coffee.covidtrace.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    LiveData<List<UserEntity>> mUserEntity = null;

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * FROM users WHERE email=(:email) and password=(:password) ")
    UserEntity login(String email, String password);
    default LiveData<List<UserEntity>> getAllUsers(){
        return mUserEntity;
    };


}
