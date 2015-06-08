package com.inalbyss.android.holocalc;

import android.test.ActivityTestCase;

import junit.framework.Assert;


public class EvalExpressionTest extends ActivityTestCase {
    EvalExpression test = new EvalExpression();


    public void testParseBasicExpression() throws Exception {
        String var;

        var = test.ParseBasicExpression("5 - 5 * 5");
        Assert.assertEquals("-20", var);

        var = test.ParseBasicExpression("5.258 - 0.025 * 584.25 / 2");
        Assert.assertEquals("-2.0451250000000005", var);

        var = test.ParseBasicExpression("50%");
        Assert.assertEquals("0.5", var);

        var = test.ParseBasicExpression("10 + 10%");
        Assert.assertEquals("11", var);

        var = test.ParseBasicExpression("10 * 10%");
        Assert.assertEquals("1", var);

        var = test.ParseBasicExpression("10 * 1 * 50%");
        Assert.assertEquals("5", var);

        var = test.ParseBasicExpression("50% * 10");
        Assert.assertEquals("5", var);

        var = test.ParseBasicExpression("10% * 50%");
        Assert.assertEquals("0.05", var);

        var = test.ParseBasicExpression("10 / 50%");
        Assert.assertEquals("20", var);

        var = test.ParseBasicExpression("50% / 10");
        Assert.assertEquals("0.05", var);

        var = test.ParseBasicExpression("10% / 50%");
        Assert.assertEquals("0.2", var);

        var = test.ParseBasicExpression("50% + 10");
        Assert.assertEquals("10.5", var);

        var = test.ParseBasicExpression("10% + 50%");
        Assert.assertEquals("0.15000000000000002", var);

        var = test.ParseBasicExpression("10 - 50%");
        Assert.assertEquals("5", var);

        var = test.ParseBasicExpression("50% - 10");
        Assert.assertEquals("-9.5", var);

        var = test.ParseBasicExpression("10% - 50%");
        Assert.assertEquals("0.05", var);

        var = test.ParseBasicExpression("10 + 5 + 10%");
        Assert.assertEquals("15.5", var);

        var = test.ParseBasicExpression("10 + 5 + 15 + 20%");
        Assert.assertEquals("33", var);

        var = test.ParseBasicExpression("10 + 5 + 15% + 20%");
        Assert.assertEquals("16.9", var);

        var = test.ParseBasicExpression("10 - 5 - 10%");
        Assert.assertEquals("5.5", var);

        var = test.ParseBasicExpression("10 + 5 * 15%");
        Assert.assertEquals("10.75", var);

        var = test.ParseBasicExpression("10 + 5 * 20% * 10%");
        Assert.assertEquals("10.1", var);

        var = test.ParseBasicExpression("10 + 5 * 15 * 10%");
        Assert.assertEquals("17.5", var);

        var = test.ParseBasicExpression("10 - 5 / 15%");
        Assert.assertEquals("-23.333333333333336", var);

        var = test.ParseBasicExpression("100 * 50% + 10%");
        Assert.assertEquals("55", var);

        var = test.ParseBasicExpression("100 / 50% - 10%");
        Assert.assertEquals("180", var);

        var = test.ParseBasicExpression("100 + 50% * 10%");
        Assert.assertEquals("15", var);

        var = test.ParseBasicExpression("100 - 50% / 10%");
        Assert.assertEquals("500", var);
    }


    public void testDeleteLast() throws Exception {
        String var;

        var = test.deleteLast("5 + 2");
        Assert.assertEquals("5 + ", var);

        var = test.deleteLast("");
        Assert.assertEquals("NaN", var);

        var = test.deleteLast("5 * 3 * ");
        Assert.assertEquals("5 * 3", var);

        var = test.deleteLast("12345");
        Assert.assertEquals("1234", var);
    }


    public void testIsNumeric() throws Exception {
        boolean var;

        var = test.isNumeric("0.0");
        Assert.assertEquals(true, var);

        var = test.isNumeric("53.23EE2");
        Assert.assertEquals(false, var);

        var = test.isNumeric("53E.23E2");
        Assert.assertEquals(false, var);

        var = test.isNumeric("");
        Assert.assertEquals(false, var);

        var = test.isNumeric(" ");
        Assert.assertEquals(false, var);

        var = test.isNumeric("abcdefg");
        Assert.assertEquals(false, var);

        var = test.isNumeric("abc.def");
        Assert.assertEquals(false, var);

        var = test.isNumeric("25243");
        Assert.assertEquals(true, var);

        var = test.isNumeric("543.453");
        Assert.assertEquals(true, var);

        var = test.isNumeric("53.23E2");
        Assert.assertEquals(true, var);

        var = test.isNumeric("0");
        Assert.assertEquals(true, var);

        var = test.isNumeric("5E2");
        Assert.assertEquals(true, var);

        var = test.isNumeric("3EE");
        Assert.assertEquals(false, var);

        var = test.isNumeric("45%333");
        Assert.assertEquals(false, var);

        var = test.isNumeric("45%");
        Assert.assertEquals(true, var);
    }


    public void testIsDecimalValue() throws Exception {
        boolean var;

        var = test.isDecimalValue(0);
        Assert.assertEquals(false, var);

        var = test.isDecimalValue(0.0);
        Assert.assertEquals(false, var);

        var = test.isDecimalValue(7654.0);
        Assert.assertEquals(false, var);

        var = test.isDecimalValue(7543.8765);
        Assert.assertEquals(true, var);

        var = test.isDecimalValue(1.1);
        Assert.assertEquals(true, var);

        var = test.isDecimalValue(1.0001);
        Assert.assertEquals(true, var);

        var = test.isDecimalValue(1E12);
        Assert.assertEquals(false, var);

        var = test.isDecimalValue(4.34E9);
        Assert.assertEquals(true, var);
    }


    public void testIsPercentage() throws Exception {
        boolean var;

        var = test.isPercentage("45%");
        Assert.assertEquals(true, var);

        var = test.isPercentage("45%333");
        Assert.assertEquals(false, var);

        var = test.isPercentage("4.5%");
        Assert.assertEquals(true, var);

        var = test.isPercentage("0%");
        Assert.assertEquals(true, var);

        var = test.isPercentage("0.01%");
        Assert.assertEquals(true, var);

        var = test.isPercentage("45 %");
        Assert.assertEquals(false, var);

        var = test.isPercentage("45%%");
        Assert.assertEquals(false, var);

        var = test.isPercentage("%45");
        Assert.assertEquals(false, var);

        var = test.isPercentage("45");
        Assert.assertEquals(false, var);

        var = test.isPercentage("");
        Assert.assertEquals(false, var);
    }


    public void testIsDecimalValue1() throws Exception {
        boolean var;

        var = test.isDecimalValue("0");
        Assert.assertEquals(false, var);

        var = test.isDecimalValue("0.0");
        Assert.assertEquals(false, var);

        var = test.isDecimalValue("7654.0");
        Assert.assertEquals(false, var);

        var = test.isDecimalValue("7543.8765");
        Assert.assertEquals(true, var);

        var = test.isDecimalValue("1.1");
        Assert.assertEquals(true, var);

        var = test.isDecimalValue("1.0001");
        Assert.assertEquals(true, var);

        var = test.isDecimalValue("4.34E9");
        Assert.assertEquals(true, var);

        var = test.isDecimalValue("1E12");
        Assert.assertEquals(false, var);
    }


    public void testInvertSignNumber() throws Exception {
        CharSequence var;

        var = test.invertSignNumber("525");
        Assert.assertEquals("-525", var);

        var = test.invertSignNumber("");
        Assert.assertEquals("", var);

        var = test.invertSignNumber("1.545E8");
        Assert.assertEquals("-1.545E8", var);

        var = test.invertSignNumber("NaN");
        Assert.assertEquals("NaN", var);

        var = test.invertSignNumber("2 * 5");
        Assert.assertEquals("2 * -5", var);

        var = test.invertSignNumber("2 * 5%");
        Assert.assertEquals("2 * -5%", var);

        var = test.invertSignNumber("-4.57%");
        Assert.assertEquals("4.57%", var);
    }
}
