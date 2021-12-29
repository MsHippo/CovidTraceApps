package com.coffee.covidtrace.Data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "hotspot_table")
public class HotSpot implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public int logitude;
    public  int latitude;
    public int cases;

    public HotSpot(int id, int logitude, int latitude, int cases) {
        this.id = id;
        this.logitude = logitude;
        this.latitude = latitude;
        this.cases = cases;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLogitude() {
        return logitude;
    }

    public void setLogitude(int logitude) {
        this.logitude = logitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }
}
