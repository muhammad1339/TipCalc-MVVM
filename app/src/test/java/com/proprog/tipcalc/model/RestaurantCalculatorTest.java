package com.proprog.tipcalc.model;


import junit.framework.Assert;

import static junit.framework.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RestaurantCalculatorTest {
    private RestaurantCalculator calculator;

    @Before
    public void setup() {
        calculator = new RestaurantCalculator();
    }

    @Test
    public void testCalculateTip() {
        int tipPct = 10;
        double checkAmount = 150;
        TipCalculations expectedTip = new TipCalculations(checkAmount, tipPct, 15.0, 165.0);
        TipCalculations actualTip = calculator.calculateTip(checkAmount, tipPct);
        assertEquals(expectedTip.getTipAmount(), actualTip.getTipAmount());
        assertEquals(expectedTip.getGrandTotal(), actualTip.getGrandTotal());

    }
}
