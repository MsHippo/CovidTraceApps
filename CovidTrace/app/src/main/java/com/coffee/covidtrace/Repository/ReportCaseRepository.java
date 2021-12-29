package com.coffee.covidtrace.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.coffee.covidtrace.Data.Database;
import com.coffee.covidtrace.Data.ReportCase;
import com.coffee.covidtrace.Data.ReportCaseDao;

import java.util.List;

public class ReportCaseRepository {

    private final ReportCaseDao reportCaseDao;
    private final LiveData<List<ReportCase>> allCases;
    int id;

    public ReportCaseRepository(Application application) {
        Database database = Database.getDatabase(application);
        reportCaseDao = database.reportCaseDao();
        allCases = reportCaseDao.getAllCases(id);
    }

    public LiveData<List<ReportCase>> getAllCases(int id) {
        return reportCaseDao.getAllCases(id);
    }

    public void insert(ReportCase reportCase) {
        Database.databaseWriteExecutor.execute( () -> reportCaseDao.addReportCases(reportCase));
    }
//    public LiveData<Task> get(long id) { return taskDao.get(id); }
//
//    public void update(Task task) {
//        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.update(task));
//    }
//
//    public void delete(Task task) {
//        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.delete(task));
//    }

}
