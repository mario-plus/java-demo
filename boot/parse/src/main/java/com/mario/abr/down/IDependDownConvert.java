package com.mario.abr.down;


import com.mario.metadata.Element;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月25日 15:58
 * 数据依赖，如head中有个数据，依赖整个body的数据，则可以实现这个方法,不需要返回值,doDependConvert直接填充目标数据
 */
public abstract class IDependDownConvert extends IElementDownConvert<Object, List<Element>> {


    @Override
    public Object doElementDownConvert(List<Element> elements, Element element) {
        doDependConvert(elements, element);
        return null;
    }

    protected abstract void doDependConvert(List<Element> elements, Element element);
}
