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

    // Constructor of History model - create the object and initialize the values
    public History(String location, String date) {
        this.location = location;
        this.date = date;
    }

    public History(FragmentActivity activity, Class<SuccessCheckInActivity> successCheckInActivityClass) {
    }

    protected History(Parcel in) {
        id = in.readInt();
        location = in.readString();
        date = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(location);
        dest.writeString(date);
    }
}