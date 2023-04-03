package com.mario.abr;


public interface IConvert<R,P,M> {
    R convert(P p,M m) throws Exception;
}
