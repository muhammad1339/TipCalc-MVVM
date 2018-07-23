package com.proprog.tipcalc.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tip_calculations")
public class TipCalculations {
    @NonNull
    @PrimaryKey
    private String tipLocName;

    private double checkAmount;
    private int tipPct;
    private double tipAmount;
    private double grandTotal;

    public TipCalculations(@NonNull String tipLocName, double checkAmount, int tipPct, double tipAmount, double grandTotal) {
        this.tipLocName = tipLocName;
        this.checkAmount = checkAmount;
        this.tipPct = tipPct;
        this.tipAmount = tipAmount;
        this.grandTotal = grandTotal;
    }

    @Ignore
    public TipCalculations(double checkAmount, int tipPct, double tipAmount, double grandTotal) {
        this.checkAmount = checkAmount;
        this.tipPct = tipPct;
        this.tipAmount = tipAmount;
        this.grandTotal = grandTotal;
    }

    public void setTipLocName(@NonNull String tipLocName) {
        this.tipLocName = tipLocName;
    }

    public String getTipLocName() {
        return tipLocName;
    }

    public double getCheckAmount() {
        return checkAmount;
    }

    public int getTipPct() {
        return tipPct;
    }

    public double getTipAmount() {
        return tipAmount;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    @Override
    public String toString() {
        return "TipCalculations{" +
                "checkAmount=" + checkAmount +
                ", tipPct=" + tipPct +
                ", tipAmount=" + tipAmount +
                ", grandTotal=" + grandTotal +
                '}';
    }
}
