package com.mario.annotation;

import org.jeasy.rules.annotation.*;

/**
 * @author zxz
 * @date 2023年06月01日 10:15
 */
@Rule
public class SecondRule {

    @Condition
    public boolean isFiveSevenRule (@Fact("number") Integer number) {
        return true;
    }

    @Action
    public void printInput(@Fact("number") Integer number) {
        System.out.println(number);
    }

    @Priority
    public int getPriority() {
        return 2;
    }
}
