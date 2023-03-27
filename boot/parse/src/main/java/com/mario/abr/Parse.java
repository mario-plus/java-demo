package com.mario.abr;

import com.mario.metadata.CmdInfo;
import com.mario.metadata.Element;
import com.mario.push.PushInfo;

import java.util.List;


/**
 * @author zxz
 * @date 2023年03月24日 11:18
 */
public interface Parse {

    //赋值
    List<Element> setCmdInfoValue(PushInfo pushInfo, CmdInfo cmdInfo);

}
