package com.proprog.tipcalc.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TipCalculationRepositry {
    Map<String, TipCalculations> calculationsMap;

    public TipCalculationRepositry() {
        calculationsMap = new HashMap<>();
    }

    public void saveTip(TipCalculations tc) {
        calculationsMap.put(tc.getTipLocName(), tc);
    }

    public TipCalculations loadTipByName(String locName){
        return calculationsMap.get(locName);
    }

    public MutableLiveData<List<TipCalculations>> loadSavedTips(){
        MutableLiveData<List<TipCalculations>> liveData = new MutableLiveData<>();
        List<TipCalculations> calculations = new ArrayList<>();
        calculations.addAll(calculationsMap.values());
        liveData.setValue(calculations);
        return liveData;
    }
}
