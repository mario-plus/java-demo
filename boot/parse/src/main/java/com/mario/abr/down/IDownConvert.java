package com.mario.abr.down;

import com.mario.abr.IConvert;

/**
 * @author zxz
 * @date 2023年03月23日 10:38
 */
public abstract class IDownConvert<R, P, M> implements IConvert<R, P, M> {

    @Override
    public R convert(P p, M m) {
        return doDownConvert(p, m);
    }

    protected abstract R doDownConvert(P p, M m);
}
