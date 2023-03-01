package com.mario.service;

import com.mario.msg.AbrContext;
import com.mario.msg.AbrParams;
import com.mario.msg.AbrResult;


public interface Procedure<PARAM extends AbrParams, CONTEXT extends AbrContext, RESULT extends AbrResult> {

    CONTEXT make(PARAM param) throws Exception;

    RESULT send(CONTEXT context) throws Exception;

    boolean deal(RESULT result) throws Exception;

}
