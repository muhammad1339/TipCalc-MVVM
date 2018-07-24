package com.proprog.tipcalc.viewmodel;

import android.util.Log;

import com.proprog.tipcalc.model.TipCalculationRepositry;

public class TipCalculationSummaryItem {
    public String tipTotal = "";
    public String tipName = "";

    public TipCalculationSummaryItem(String tipName, String tipTotal) {
        this.tipTotal = tipTotal;
        this.tipName = tipName;
    }


}
