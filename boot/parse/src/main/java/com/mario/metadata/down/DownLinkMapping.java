package com.mario.metadata.down;

import com.mario.constants.MsgType;
import lombok.Data;

import java.util.List;

/**
 * @author zxz
 * @date 2023年03月22日 20:31
 */
@Data
public class DownLinkMapping {

    private String serviceName;

    /**
     * 下行数据类型
     * {@link MsgType}
     */
    private String msgType;


    /**
     * 服务转换类 bean
     */
    private String serviceConverter;


    private List<CmdInfo> data;


    public CmdInfo getCmdInfoByCmd(String cmd) {
        return data.stream().filter(e -> e.getCmd().equals(cmd)).findFirst().get();
    }

}
