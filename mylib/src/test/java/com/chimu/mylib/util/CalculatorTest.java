package com.chimu.mylib.util;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Longwj on 2017/11/8.
 */
public class CalculatorTest {
    private Calculator mCalculator;
    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void sum() throws Exception {
        assertEquals(5D,mCalculator.sum(3,2),0.1);
    }

    @Test
    public void substract() throws Exception {
        assertEquals(1,mCalculator.substract(3,2),0.1);
    }

    @Test
    public void divide() throws Exception {
        assertEquals(1.5,mCalculator.divide(3,2),0.1);
    }

    @Test
    public void multiply() throws Exception {
        assertEquals(6,mCalculator.multiply(3,2),0.1);
    }

}