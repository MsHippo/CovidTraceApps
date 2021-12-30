package com.coffee.covidtrace.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.FragmentActivity;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.coffee.covidtrace.Ui.record.SuccessCheckInActivity;

@Entity(tableName = "noti_table")
public class NotificationEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String detail;
    private String date;
    private String time;
    //adding to record user id
    private int user_id;



    // Constructor of notification model - create the object and initialize the values
    public NotificationEntity(String detail, String date, int user_id, String time) {
        this.detail = detail;
        this.date = date;
        this.user_id = user_id;
        this.time = time;
    }

    @Ignore
    public NotificationEntity(){

    }

    protected NotificationEntity(Parcel in) {
        id = in.readInt();
        detail = in.readString();
        date = in.readString();
        user_id = in.readInt();
        time = in.readString();
    }

    public static final Creator<NotificationEntity> CREATOR = new Creator<NotificationEntity>() {
        @Override
        public NotificationEntity createFromParcel(Parcel in) {
            return new NotificationEntity(in);
        }

        @Override
        public NotificationEntity[] newArray(int size) {
            return new NotificationEntity[size];
        }
    };

    //getter methods and setter methods omitted because there is no update/delete function
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTime() {
        return time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(detail);
        dest.writeString(date);
        dest.writeInt(user_id);
        dest.writeString(time);
    }
}