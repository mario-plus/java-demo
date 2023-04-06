package com.mario.abr;

import com.alibaba.fastjson2.JSONObject;
import com.mario.abr.down.ICmdDownConvert;
import com.mario.abr.down.IDependDownConvert;
import com.mario.constants.Constants;
import com.mario.metadata.down.CmdInfo;
import com.mario.metadata.Element;
import com.mario.push.PushInfo;
import com.mario.util.ReflectUtil;
import com.mario.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zxz
 * @date 2023年03月22日 14:44
 * 解析物模型数据
 */
@Slf4j
public abstract class AbrDownParseToBytes extends ICmdDownConvert implements DownParse {

    ReflectUtil reflectUtil;

    public AbrDownParseToBytes(ReflectUtil reflectUtil) {
        this.reflectUtil = reflectUtil;
    }

    @Override
    public byte[] doCmdDownConvert(PushInfo pushInfo, CmdInfo cmdInfo) {
        return doDownConvertByParse(pushInfo, cmdInfo);
    }

    protected abstract byte[] doDownConvertByParse(PushInfo pushInfo, CmdInfo cmdInfo);


    @Override
    public List<Element> setCmdInfoValue(PushInfo pushInfo, CmdInfo cmdInfo) {
        if (setElementValue(pushInfo, cmdInfo.getHead())) doAfter(cmdInfo.getHead(), cmdInfo);
        if (setElementValue(pushInfo, cmdInfo.getBody())) doAfter(cmdInfo.getBody(), cmdInfo);
        if (setElementValue(pushInfo, cmdInfo.getTail())) doAfter(cmdInfo.getTail(), cmdInfo);
        List<Element> elements = new ArrayList<>();
        elements.addAll(cmdInfo.getHead());
        elements.addAll(cmdInfo.getBody());
        elements.addAll(cmdInfo.getTail());
        return elements;
    }

    public void doAfter(List<Element> elements, CmdInfo cmdInfo) {
        elements.stream().filter(element -> StringUtil.j1Data(element.getValue().toString()))
                .forEach(element -> {
                    IDependDownConvert bean = (IDependDownConvert) reflectUtil.getBean(element.getElementConvert());
                    bean.doElementDownConvert(getDepends(StringUtil.getValueFromS1(element.getValue().toString()), cmdInfo), element);
                });
    }


    public boolean setElementValue(PushInfo pushInfo, List<Element> elementList) {
        if (elementList == null || elementList.isEmpty()) {
            return false;
        }
        AtomicBoolean doNext = new AtomicBoolean(false);
        JSONObject downData = (JSONObject) pushInfo.getContext();
        elementList.forEach(element -> {
            Object valueFromD = null;
            try {
                if (element.getValue() != null && StringUtil.j1Data(element.getValue().toString())) {//#{}数据后续处理
                    doNext.set(true);
                    return;
                }
                if (element.getElementConvert() != null && !"".equals(element.getElementConvert())) {//自定义转换器
                    valueFromD = reflectUtil.callByStr(element.getElementConvert(), downData);
                } else {
                    if (element.getType().equals(Constants.dynamic) && StringUtil.s1Data(element.getValue().toString())) {//静态数据不做处理，直接填充
                        valueFromD = StringUtil.getValueFromD(StringUtil.getValueFromS1(element.getValue().toString()), downData);
                    }
                    if (StringUtil.j1Data(element.getValue().toString())) {
                        doNext.set(true);
                    }
                }
            } catch (Exception e) {
                log.error("error to parse data:{}", element);
                e.printStackTrace();
            }
            if (element.getType().equals(Constants.dynamic)) {
                setTargetValue(element, valueFromD);
            }
        });
        return doNext.get();
    }

    public void setTargetValue(Element element, Object valueFromD) {
        if (element.getValue() != null && element.getValue().toString().startsWith("#{")) {//#{}数据最后处理
            return;
        }
        if (element.getDefaultKey() != null
                && !"".equals(element.getDefaultKey())
                && element.getDefaultKey().equals(valueFromD.toString())) {//填充默认数据
            element.setValue(element.getDefaultValue());
            return;
        }
        if (element.getByteGroup() != null) {//按位解析
            //转成2进制

        }
        setElementTargetValue(element, valueFromD);
    }

    public List<Element> getDepends(String content, CmdInfo info) {
        switch (content) {
            case "head":
                return info.getHead();
            case "body":
                return info.getBody();
            case "tail":
                return info.getTail();
            default:
                return new ArrayList<>();
        }
    }
}
