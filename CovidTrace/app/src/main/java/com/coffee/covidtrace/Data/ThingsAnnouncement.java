package com.coffee.covidtrace.Data;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.fragment.app.FragmentActivity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.coffee.covidtrace.Ui.record.SuccessCheckInActivity;

import java.sql.Blob;
import java.sql.Date;

@Entity(tableName = "thingToDo_table")
public class ThingsAnnouncement implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] pic_authorities;

    @ColumnInfo(name = "name_authorities")
    private String name_authorities;

    @ColumnInfo(name = "date", defaultValue = "CURRENT_DATE")
    private String date;

    @ColumnInfo(name = "time", defaultValue = "CURRENT_TIME")
    private String time;

    @ColumnInfo(name = "tx_anouncement")
    private String tx_anouncement;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] pic_awareness;

    //getter and setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPic_authorities() {
        return pic_authorities;
    }

    public void setPic_authorities(byte[] pic_authorities) {
        this.pic_authorities = pic_authorities;
    }

    public String getName_authorities() {
        return name_authorities;
    }

    public void setName_authorities(String name_authorities) {
        this.name_authorities = name_authorities;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTx_anouncement() {
        return tx_anouncement;
    }

    public void setTx_anouncement(String tx_anouncement) {
        this.tx_anouncement = tx_anouncement;
    }

    public byte[] getPic_awareness() {
        return pic_awareness;
    }

    public void setPic_awareness(byte[] pic_awareness) {
        this.pic_awareness = pic_awareness;
    }

    public static Creator<ThingsAnnouncement> getCREATOR() {
        return CREATOR;
    }

// Constructor of History model - create the object and initialize the values

    public ThingsAnnouncement(byte[] pic_authorities, String name_authorities, String date, String time, String tx_anouncement, byte[] pic_awareness) {
        this.pic_authorities = pic_authorities;
        this.name_authorities = name_authorities;
        this.date = date;
        this.time = time;
        this.tx_anouncement = tx_anouncement;
        this.pic_awareness = pic_awareness;
    }

//    @Ignore
//    public ThingsAnnouncement(byte[] pic_authorities, String name_authorities, String date, String time, String tx_anouncement) {
//        this.pic_authorities = pic_authorities;
//        this.name_authorities = name_authorities;
//        this.date = date;
//        this.time = time;
//        this.tx_anouncement = tx_anouncement;
//    }
    //parcelable

    public ThingsAnnouncement(FragmentActivity activity, Class<SuccessCheckInActivity> successCheckInActivityClass) {
    }
    public ThingsAnnouncement(Parcel in) {
        id = in.readInt();
        pic_authorities = in.createByteArray();
        name_authorities = in.readString();
        date = in.readString();
        time = in.readString();
        tx_anouncement = in.readString();
        pic_awareness = in.createByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByteArray(pic_authorities);
        dest.writeString(name_authorities);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(tx_anouncement);
        dest.writeByteArray(pic_awareness);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ThingsAnnouncement> CREATOR = new Creator<ThingsAnnouncement>() {
        @Override
        public ThingsAnnouncement createFromParcel(Parcel in) {
            return new ThingsAnnouncement(in);
        }

        @Override
        public ThingsAnnouncement[] newArray(int size) {
            return new ThingsAnnouncement[size];
        }
    };
}
