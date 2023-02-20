package com.mario;

/**
 * @author zxz
 * @date 2023年02月20日 17:05
 */
public class MockT {
    public static void main(String[] args) {
        try {
            calculate();
        } catch (IotSmartApiException e) {
            IotSmartApiException e1 = e;

        }
    }

    private static void calculate() {
        int a =0;
        try {
            int i = 10 / a;
        } catch (Exception e) {
            throw new IotSmartApiException(10L, "分母不能为0", a);
        }
    }
}
