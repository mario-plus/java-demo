package com.mario.abr;

import com.mario.push.PushInfo;

public interface IConvert<R,P,M> {
    R convert(P p,M m);
}
