package com.mario.service;


import com.mario.msg.AbrParams;
import com.mario.msg.AbrResult;

public interface Process<PARAM extends AbrParams, RESULT extends AbrResult> {

    RESULT doProcess(PARAM param) throws Exception;

}
