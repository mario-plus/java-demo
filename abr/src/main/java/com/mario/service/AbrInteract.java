package com.mario.service;

import com.mario.msg.AbrParams;
import com.mario.msg.AbrContext;
import com.mario.msg.AbrResult;

/**
 * @author zxz
 * @date 2023年02月28日 16:51
 */
public abstract class AbrInteract<PARAM extends AbrParams, CONTEXT extends AbrContext, RESULT extends AbrResult>
        implements Procedure<PARAM, CONTEXT, RESULT>, Process<PARAM, RESULT> {


    public RESULT doProcess(PARAM abrParams) throws Exception {
        try {
            CONTEXT make = make(abrParams);
            RESULT result = send(make);
            deal(result);
            return result;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public CONTEXT make(PARAM param) throws Exception {
        try {
            CONTEXT context = makeOrder(param);
            if (context == null) {
                throw new Exception("参数不能为null");
            }
            return context;

        } catch (Exception e) {
            throw new Exception("参数组装异常");
        }
    }

    @Override
    public RESULT send(CONTEXT context) throws Exception {
        try {
            return sendOrder(context);
        } catch (Exception e) {
            throw new Exception("指令下发异常");
        }
    }

    @Override
    public boolean deal(RESULT result) throws Exception {
        boolean b;
        try {
            b = dealResult(result);
        } catch (Exception e) {
            throw new Exception("响应结果处理异常");
        }
        if (!b){
            throw new Exception("响应结果校验不通过");
        }
        return b;
    }


    protected abstract CONTEXT makeOrder(PARAM abrParams);

    protected abstract RESULT sendOrder(CONTEXT abrContext);

    protected abstract boolean dealResult(RESULT abrResult);


}
