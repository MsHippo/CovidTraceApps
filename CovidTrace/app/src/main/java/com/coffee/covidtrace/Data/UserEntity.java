package com.coffee.covidtrace.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class UserEntity implements Serializable{

    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "email")
    String email;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "nric")
    String nric;

    @ColumnInfo(name = "phone")
    String phone;

    @ColumnInfo(name = "password")
    String password;

    @ColumnInfo(name = "risk status", defaultValue = "0")
    int status;

    @ColumnInfo(name = "vaccination", defaultValue = "0")
    int vaccine;


    public UserEntity(Integer id, String email, String name,  String nric, String phone,  String password, int status, int vaccine) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.nric = nric;
        this.phone = phone;
        this.password = password;
        this.status = status;
        this.vaccine = vaccine;
    }

    public UserEntity(){

    }

//    protected UserEntity(Parcel in) {
//        if (in.readByte() == 0) {
//            id = null;
//        } else {
//            id = in.readInt();
//        }
//        email = in.readString();
//        name = in.readString();
//        nric = in.readString();
//        phone = in.readString();
//        password = in.readString();
//    }

//    public static final Creator<UserEntity> CREATOR = new Creator<UserEntity>() {
//        @Override
//        public UserEntity createFromParcel(Parcel in) {
//            return new UserEntity(in);
//        }
//
//        @Override
//        public UserEntity[] newArray(int size) {
//            return new UserEntity[size];
//        }
//    };

    //getter and setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNric() {
        return nric;
    }

    public void setNric(String nric) {
        this.nric = nric;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String  phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getVaccine() {
        return vaccine;
    }

    public void setVaccine(int vaccine) {
        this.vaccine = vaccine;
    }

    //    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        if (id == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeInt(id);
//        }
//        dest.writeString(email);
//        dest.writeString(name);
//        dest.writeString(nric);
//        dest.writeString(phone);
//        dest.writeString(password);
//    }
}
