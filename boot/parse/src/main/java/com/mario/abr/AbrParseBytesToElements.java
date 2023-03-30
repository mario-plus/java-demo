package com.mario.abr;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mario.abr.up.ICmdUpConvert;
import com.mario.abr.up.IServiceUpConvert;
import com.mario.abr.up.IUpConvert;
import com.mario.metadata.Element;
import com.mario.metadata.up.UpInfo;
import com.mario.metadata.up.UpLinkMapping;
import com.mario.util.ReflectUtil;
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
    protected List<Element> doUpConvert(byte[] bytes, UpLinkMapping upLinkMapping) throws Exception {
        String serviceConverter = upLinkMapping.getServiceConverter();
        if (serviceConverter != null && !"".equals(serviceConverter)) {
            IServiceUpConvert bean = (IServiceUpConvert) reflectUtil.applicationContext.getBean(serviceConverter);
            return bean.doUpConvert(bytes, upLinkMapping);
        }
        String cmdValue = getCmdValue(bytes, upLinkMapping);
        if (cmdValue == null) {
            throw new Exception("无法获取上行数据物模型key");
        }
        UpInfo cmdInfoByCmd = upLinkMapping.getCmdInfoByCmd(cmdValue);
        if (cmdInfoByCmd.getCmdConvert() != null && !"".equals(cmdInfoByCmd.getCmdConvert())) {
            ICmdUpConvert bean = (ICmdUpConvert) reflectUtil.applicationContext.getBean(cmdInfoByCmd.getCmdConvert());
            return bean.doUpConvert(bytes, upLinkMapping);
        }
        return convertBytesToData(bytes, cmdInfoByCmd);
    }

    protected abstract List<Element> convertBytesToData(byte[] bytes, UpInfo upInfo);


}
