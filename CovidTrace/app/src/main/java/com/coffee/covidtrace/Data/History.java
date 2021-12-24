package com.coffee.covidtrace.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.FragmentActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.coffee.covidtrace.Ui.record.SuccessCheckInActivity;

@Entity(tableName = "history_table")
public class History implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String location;
    private String date;
    private String time;
    //adding to record user id
    private int user_id;



    // Constructor of History model - create the object and initialize the values
    public History(String location, String date, int user_id, String time) {
        this.location = location;
        this.date = date;
        this.user_id = user_id;
        this.time = time;
    }

    public History(FragmentActivity activity, Class<SuccessCheckInActivity> successCheckInActivityClass) {
    }

    protected History(Parcel in) {
        id = in.readInt();
        location = in.readString();
        date = in.readString();
        user_id = in.readInt();
        time = in.readString();
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    //getter methods and setter methods omitted because there is no update/delete function
    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
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
        dest.writeString(location);
        dest.writeString(date);
        dest.writeInt(user_id);
        dest.writeString(time);
    }
}