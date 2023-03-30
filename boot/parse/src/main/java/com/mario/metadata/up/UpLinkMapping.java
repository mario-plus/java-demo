package com.mario.metadata.up;

import com.mario.constants.MsgType;
import com.mario.metadata.Element;
import lombok.Data;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月22日 20:31
 */
@Data
public class UpLinkMapping {

    private String serviceName;

    /**
     * 上行数据目标格式{@link MsgType}
     * 协议格式:
     * 如直接转成String进行解析{@link com.mario.parse.up.ParseBytesToStr}
     * 如转成Json进行解析{@link com.mario.parse.up.ParseBytesToJsonStr}
     * 如直接解析Hex{@link com.mario.parse.up.ParseBytesDirect}
     */
    private String msgType;

    /**
     * 服务转换类 bean{@link com.mario.abr.up.IServiceUpConvert}
     */
    private String serviceConverter;

    /**
     * {@link Element#getValue()}用于匹配cmd
     */
    private Element cmdKey;


    private List<UpInfo> data;

    public UpInfo getCmdInfoByCmd(String cmd) {
        return data.stream().filter(e -> e.getCmd().equals(cmd)).findFirst().get();
    }
}
