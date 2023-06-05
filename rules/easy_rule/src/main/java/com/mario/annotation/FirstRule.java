package com.mario.annotation;

import org.jeasy.rules.annotation.*;

/**
 * @author zxz
 * @date 2023年06月01日 10:01
 */
@Rule
public class FirstRule {

    @Condition
    public boolean isFiveSevenRule(@Fact("number") Integer number) {
        return number % 5 == 0;
    }

    @Action(order = 1)
    public void action1(@Fact("number")Integer number) {
        System.out.println("this number is: " + number);
    }

    @Priority
    public int getPriority() {
        return 1;
    }
}
