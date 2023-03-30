package com.mario.abr;

import com.mario.metadata.up.UpLinkMapping;



/**
 * @author zxz
 * @date 2023年03月28日 11:13
 */
public interface UpParse extends Parse {

    String getCmdValue(byte[] bytes, UpLinkMapping upLinkMapping);

}
