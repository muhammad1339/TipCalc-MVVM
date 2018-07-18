package com.proprog.tipcalc.viewmodel;

import android.app.Application;

import com.proprog.tipcalc.model.RestaurantCalculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;

public class ClacViewModelTest {

    CalcViewModel calcViewModel;

    @Mock
    RestaurantCalculator calculator;

    @Mock
    Application application;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        calculator = new RestaurantCalculator();
        calcViewModel = new CalcViewModel(calculator,application);
    }

    @Test
    public void testCalculateTip() {
        calcViewModel.inputCheckAmount = "150.0";
        calcViewModel.inputTipPercentage = "10";

        calcViewModel.tipCalculator();
        assertEquals(10,calcViewModel.tipCalculations.getTipPct());
        assertEquals(150.0,calcViewModel.tipCalculations.getCheckAmount());
        assertEquals(165.0,calcViewModel.tipCalculations.getGrandTotal());

    }
}
