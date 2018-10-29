package com.oerlemans.siemiatycze.balance.util;

import org.junit.Assert;
import org.junit.Test;

public class BalanceUtilsTestSuite {

    @Test
    public void shouldConvertToDouble() {
        //Given
        String testScaleData90 = "02,79,30,30,20,30,30,30,39,30,30,30,30,30,30,30,0D";
        String testScaleData0 = "02,79,30,30,20,30,30,30,30,30,30,30,30,30,30,30,0D";
        //When
        double doubleValueOfScaleData90 = BalanceUtils.scaleResultAsdouble(testScaleData90);
        double doubleValueOfScaleData0 = BalanceUtils.scaleResultAsdouble(testScaleData0);
        //Then
        Assert.assertEquals(90.0,doubleValueOfScaleData90, 0.01 );
        Assert.assertEquals(0.0,doubleValueOfScaleData0, 0.01 );
    }
}
