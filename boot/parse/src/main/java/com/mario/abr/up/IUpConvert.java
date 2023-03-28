package com.mario.abr.up;


import com.mario.abr.IConvert;

/**
 * @author zxz
 * @date 2023年03月23日 11:14
 */
public abstract class IUpConvert<R, P, M> implements IConvert<R, P, M> {

    @Override
    public R convert(P p, M m) {
        return doUpConvert(p, m);
    }

    protected abstract R doUpConvert(P p, M m);
}
