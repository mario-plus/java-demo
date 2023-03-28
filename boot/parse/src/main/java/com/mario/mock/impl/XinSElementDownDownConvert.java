package com.mario.mock.impl;

import com.mario.abr.down.IDependDownConvert;
import com.mario.metadata.Element;
import com.mario.util.ByteUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月25日 16:41
 */
@Component
public class XinSElementDownDownConvert extends IDependDownConvert {

    @Override
    protected void doDependConvert(List<Element> elements, Element element) {
        element.setValue(ByteUtil.getHexFromDec(elements.size(), 1));
    }

}
