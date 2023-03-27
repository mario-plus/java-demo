package com.mario.mock.impl;

import com.mario.abr.down.IDependConvert;
import com.mario.metadata.Element;
import com.mario.util.ByteUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月25日 16:41
 */
@Component
public class XinSElementDownConvert extends IDependConvert {

    @Override
    protected void doDependConvert(List<Element> elements, Element element) {
        element.setValue(ByteUtil.getHexFromDec(elements.size(), 1));
    }

}
