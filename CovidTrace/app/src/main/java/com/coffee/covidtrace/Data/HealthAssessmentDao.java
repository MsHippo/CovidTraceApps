package com.coffee.covidtrace.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HealthAssessmentDao {

    @Query("SELECT * FROM health_assessment_table " +
            "ORDER BY question_id DESC")
    LiveData<List<HealthAssessment>> getAllAssessment();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(HealthAssessment healthAssessment);
}
