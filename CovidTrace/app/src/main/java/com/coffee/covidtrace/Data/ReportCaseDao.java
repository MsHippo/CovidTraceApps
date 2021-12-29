package com.coffee.covidtrace.Data;

import android.content.ContentValues;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReportCaseDao {

    @Insert
    void addReportCases(ReportCase reportCase);

    @Query("SELECT * FROM report_case_table " +
            "WHERE report_case_table.user_id == :id " +
            "ORDER BY id DESC")
    LiveData<List<ReportCase>> getAllCases(int id);
}
