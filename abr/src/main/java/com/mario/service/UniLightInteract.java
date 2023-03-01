package com.mario.service;


import com.mario.msg.AbrContext;
import com.mario.msg.AbrParams;
import com.mario.msg.AbrResult;
import com.mario.msg.uniLight.UniLightContext;
import com.mario.msg.uniLight.UniLightParam;
import com.mario.msg.uniLight.UniLightResult;


/**
 * @author zxz
 * @date 2023年02月28日 17:33
 */
public abstract class UniLightInteract<PARAM extends UniLightParam, CONTEXT extends UniLightContext, RESULT extends UniLightResult>
        extends AbrInteract<UniLightParam, UniLightContext, UniLightResult> {


    @Override
    public UniLightResult doProcess(UniLightParam abrParams) throws Exception {
        try {
            init((PARAM) abrParams);
        } catch (Exception e) {
            throw new Exception("数据校验异常");
        }
        return super.doProcess(abrParams);
    }

    @Override
    protected UniLightContext makeOrder(UniLightParam abrParams) {
        return  makeLightOrder((PARAM) abrParams);
    }

    @Override
    protected UniLightResult sendOrder(UniLightContext abrContext) {
        return sendLightOrder((CONTEXT) abrContext);
    }

    @Override
    protected boolean dealResult(UniLightResult abrResult) {
        return dealLightRes((RESULT) abrResult);
    }

    protected abstract void init(PARAM param);

    protected abstract CONTEXT makeLightOrder(PARAM param);

    protected abstract RESULT sendLightOrder(CONTEXT context);

    protected abstract boolean dealLightRes(RESULT result);

}
