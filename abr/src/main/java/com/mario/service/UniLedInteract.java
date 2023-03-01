package com.mario.service;

import com.mario.msg.uniLed.UniLedContext;
import com.mario.msg.uniLed.UniLedParam;
import com.mario.msg.uniLed.UniLedResult;

/**
 * @author zxz
 * @date 2023年02月28日 17:46
 */
public abstract class UniLedInteract<PARAM extends UniLedParam,CONTEXT extends UniLedContext,RESULT extends UniLedResult>
        extends AbrInteract<PARAM,CONTEXT,RESULT>{


}
