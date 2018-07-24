package com.proprog.tipcalc.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TipCalculationRepositry {

    private TipDAO dao;
    private TipDB db;

    public TipCalculationRepositry(Application application) {
        db = TipDB.GET_DB_INSTANCE(application, false);
        dao = db.getTipDAO();
    }

    public void saveTip(TipCalculations tc) {
        new InsertTask().execute(tc);
    }

    public TipCalculations loadTipByName(String locName) throws ExecutionException, InterruptedException {
        return new LoadByNameTask().execute(locName).get();
    }

    public LiveData<List<TipCalculations>> loadSavedTips() {
        return dao.getAllTips();
    }

    public void delete(String tipName) {
        new DeleteByNameTask().execute(tipName);
    }

    private class InsertTask extends AsyncTask<TipCalculations, Void, Void> {

        @Override
        protected Void doInBackground(TipCalculations... tipCalculations) {
            dao.insertTip(tipCalculations[0]);
            return null;
        }
    }

    private class LoadByNameTask extends AsyncTask<String, Void, TipCalculations> {


        @Override
        protected TipCalculations doInBackground(String... strings) {
            return dao.getSingleTip(strings[0]);
        }

    }

    private class DeleteByNameTask extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... strings) {
            dao.deleteSingleTip(strings[0]);
            return null;
        }

    }
}
