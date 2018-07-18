package com.proprog.tipcalc.viewmodel;

public class TipCalculationSummaryItem {
    public String tipTotal = "";
    public String tipName = "";

    public TipCalculationSummaryItem(String tipName, String tipTotal) {
        this.tipTotal = tipTotal;
        this.tipName = tipName;
    }
}
