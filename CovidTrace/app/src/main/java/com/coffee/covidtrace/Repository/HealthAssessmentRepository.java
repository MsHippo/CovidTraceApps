package com.coffee.covidtrace.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.HealthAssessment;
import com.coffee.covidtrace.Data.HealthAssessmentDao;
import com.coffee.covidtrace.Data.ThingsAnnouncement;
import com.coffee.covidtrace.Data.ThingsAnnouncementDao;

import java.util.List;

public class HealthAssessmentRepository {

    private HealthAssessmentDao healthAssessmentDao;
    private LiveData<List<HealthAssessment>> mAllAssessment;
    private Database db;

    public HealthAssessmentRepository(Application application) {
        db = Database.getDatabase(application);
        healthAssessmentDao = db.healthAssessmentDao();
        mAllAssessment = healthAssessmentDao.getAllAssessment();
    }

    public LiveData<List<HealthAssessment>> getAllAssessment(){
        return mAllAssessment;
    }
}
