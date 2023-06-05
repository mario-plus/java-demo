package com.mario.test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author zxz
 * @date 2023年05月24日 10:47
 */
public class tyt {
    public static void main(String[] args) {
        System.out.println(getLightPower(0.38,220.0));
    }
    public static double getLightPower(double voltage, double current) {
        BigDecimal b1 = new BigDecimal(Double.toString(voltage));
        BigDecimal b2 = new BigDecimal(Double.toString(current));
        return b1.multiply(b2, MathContext.DECIMAL64).doubleValue();
    }
}
