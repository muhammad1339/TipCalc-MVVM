package com.proprog.tipcalc.viewmodel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.text.TextUtils;
import android.util.Log;

import com.proprog.tipcalc.R;
import com.proprog.tipcalc.model.RestaurantCalculator;
import com.proprog.tipcalc.model.TipCalculationRepositry;
import com.proprog.tipcalc.model.TipCalculations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class CalcViewModel extends ViewModel {
    private TipCalculationRepositry repositry;
    public String inputCheckAmount = "";
    public String inputTipPercentage = "";
    private RestaurantCalculator calculator;


    private static TipCalculations lastTip = new TipCalculations(0, 0, 0, 0);
    public String outputCheckAmount = "";
    public String outputTipAmount = "";
    public String outputTotalAmount = "";
    public String tipName = "";


    public CalcViewModel(Application application) {
        super(application);
        this.calculator = new RestaurantCalculator();
        this.repositry = new TipCalculationRepositry(application);
        updateOutput(new TipCalculations(0, 0, 0, 0));
    }

    private void updateOutput(TipCalculations tc) {
        lastTip = tc;
        outputCheckAmount = getApplication().getString(R.string.formatted_amount_text
                , lastTip.getCheckAmount());
        outputTipAmount = getApplication().getString(R.string.formatted_amount_text
                , lastTip.getTipAmount());
        outputTotalAmount = getApplication().getString(R.string.formatted_amount_text
                , lastTip.getGrandTotal());
        tipName = lastTip.getTipLocName();
        notifyChange();
    }

    public void saveTip(String tipName) {
        lastTip.setTipLocName(tipName);
        TipCalculations tipToSave = lastTip;
        repositry.saveTip(tipToSave);
        updateOutput(tipToSave);
    }

    public LiveData<List<TipCalculationSummaryItem>> loadSavedTips() {
        LiveData<List<TipCalculations>> listMutableLiveData = repositry.loadSavedTips();
        LiveData<List<TipCalculationSummaryItem>> liveData = Transformations.map(listMutableLiveData,
                input -> {
                    List<TipCalculationSummaryItem> tipCalculationSummaryItemList
                            = new ArrayList<>();
                    for (TipCalculations itemInput : input) {
                        tipCalculationSummaryItemList
                                .add(new TipCalculationSummaryItem(itemInput.getTipLocName(),
                                        getApplication().getString(R.string.formatted_amount_text, itemInput.getGrandTotal())));
                    }
                    return tipCalculationSummaryItemList;
                }
        );

        return liveData;
    }

    public void loadTip(String name) {
        Log.e("loadTip", name);
        TipCalculations tc = null;
        try {
            tc = repositry.loadTipByName(name);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (tc != null) {
            inputCheckAmount = String.valueOf(tc.getCheckAmount());
            inputTipPercentage = String.valueOf(tc.getTipPct());
            updateOutput(tc);
        }
    }

    public void tipCalculator() {
        TipCalculations tipCalculations;

        if (TextUtils.isEmpty(inputTipPercentage) || TextUtils.isEmpty(inputCheckAmount)) {
            Log.e("TIPS", "In View Model");
        } else {
            double checkAmount = Double.parseDouble(inputCheckAmount);
            int tipPct = Integer.parseInt(inputTipPercentage);
            tipCalculations = calculator.calculateTip(checkAmount, tipPct);
            updateOutput(tipCalculations);
        }
    }

}
