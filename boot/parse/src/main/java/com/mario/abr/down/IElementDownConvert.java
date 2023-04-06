package com.mario.abr.down;

import com.mario.metadata.Element;


/**
 * @author zxz
 * @date 2023年03月23日 10:12
 * 转换特殊字节
 */
public abstract class IElementDownConvert<R,P> extends IDownConvert<R, P, Element> {

    @Override
    public R doDownConvert(P p, Element element) {
        return doElementDownConvert(p, element);
    }

    protected abstract R doElementDownConvert(P p, Element element);
}
