package com.mario.abr;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mario.abr.up.ICmdKeyUpConvert;
import com.mario.abr.up.ICmdUpConvert;
import com.mario.abr.up.IServiceUpConvert;
import com.mario.abr.up.IUpConvert;
import com.mario.metadata.Element;
import com.mario.metadata.up.UpInfo;
import com.mario.metadata.up.UpLinkMapping;
import com.mario.util.ReflectUtil;
import com.mario.util.StringUtil;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月27日 20:23
 */
public abstract class AbrParseBytesToElements extends IUpConvert<List<Element>, byte[], UpLinkMapping> implements UpParse {


    public ReflectUtil reflectUtil;

    public AbrParseBytesToElements(ReflectUtil reflectUtil) {
        this.reflectUtil = reflectUtil;
    }

    @Override
    public List<Element> doUpConvert(byte[] bytes, UpLinkMapping upLinkMapping) throws Exception {
        String serviceConverter = upLinkMapping.getServiceConverter();
        if (!StringUtil.isEmpty(serviceConverter)) {//自定义service解析
            IServiceUpConvert bean = (IServiceUpConvert) reflectUtil.getBean(serviceConverter);
            return bean.doUpConvert(bytes, upLinkMapping);
        }
        String cmdValue = null;
        if (!StringUtil.isEmpty(upLinkMapping.getCmdKey().getElementConvert())) {//获取自定义物模型key
            ICmdKeyUpConvert bean = (ICmdKeyUpConvert) reflectUtil.getBean(upLinkMapping.getCmdKey().getElementConvert());
            cmdValue = bean.doCmdKeyUpConvert(bytes);
        } else {
            cmdValue = getCmdValue(bytes, upLinkMapping);//默认获取物模型key
        }
        if (cmdValue == null) {
            throw new Exception("无法获取上行数据物模型key");
        }
        UpInfo cmdInfoByCmd = upLinkMapping.getCmdInfoByCmd(cmdValue);
        if (!StringUtil.isEmpty(cmdInfoByCmd.getCmdConvert())) {//自定义单个物模型数据解析
            ICmdUpConvert bean = (ICmdUpConvert) reflectUtil.getBean(cmdInfoByCmd.getCmdConvert());
            return bean.doUpConvert(bytes, upLinkMapping);
        }
        return convertBytesToData(bytes, cmdInfoByCmd);
    }

    protected abstract List<Element> convertBytesToData(byte[] bytes, UpInfo upInfo);


}
