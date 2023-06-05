package com.mario.annotation;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngineParameters;
import org.jeasy.rules.core.DefaultRulesEngine;

/**
 * @author zxz
 * @date 2023年06月01日 10:17
 */
public class TestRules {
    public static void main(String[] args) {
        RulesEngineParameters rulesEngineParameters = new RulesEngineParameters()/*.skipOnFirstAppliedRule(true)*/;
        DefaultRulesEngine defaultRulesEngine = new DefaultRulesEngine(rulesEngineParameters);

        Rules rules = new Rules();
        rules.register(new FirstRule());
        rules.register(new SecondRule());

        Facts facts = new Facts();//参数传递，set
        for (int i = 0; i < 100; i++) {
            facts.put("number", i);
            defaultRulesEngine.fire(rules, facts);
        }
    }
}
