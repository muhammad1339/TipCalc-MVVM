package com.proprog.tipcalc.model;

import android.util.Log;

public class RestaurantCalculator {
    public TipCalculations calculateTip(double checkAmount, int tipPct) {
        double tipAmount = checkAmount * (tipPct / 100.0);
        double grandTotal = checkAmount + tipAmount;
        return new TipCalculations(checkAmount, tipPct, tipAmount, grandTotal);
    }
}
