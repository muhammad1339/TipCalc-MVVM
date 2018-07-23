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

public class TipCalculationRepositry {

    private TipDAO dao;
    private TipDB db;
//    Map<String, TipCalculations> calculationsMap;

    public TipCalculationRepositry(Application application) {
        db = TipDB.GET_DB_INSTANCE(application, false);
        dao = db.getTipDAO();
//        calculationsMap = new HashMap<>();
    }

    public void saveTip(TipCalculations tc) {
        new InsertTask().execute(tc);
    }

    public TipCalculations loadTipByName(String locName) {
        return dao.getSingleTip(locName);
    }

    public LiveData<List<TipCalculations>> loadSavedTips() {
//        MutableLiveData<List<TipCalculations>> liveData = new MutableLiveData<>();
//        List<TipCalculations> calculations = new ArrayList<>();
//        calculations.addAll(calculationsMap.values());
//        liveData.setValue(calculations);
        return dao.getAllTips();
    }

    private class InsertTask extends AsyncTask<TipCalculations, Void, Void> {

        @Override
        protected Void doInBackground(TipCalculations... tipCalculations) {
            dao.insertTip(tipCalculations[0]);
            return null;
        }
    }
}
