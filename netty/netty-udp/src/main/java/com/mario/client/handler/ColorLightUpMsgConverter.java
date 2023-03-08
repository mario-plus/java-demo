package com.mario.client.handler;

import com.mario.client.bo.ByteSplit;
import com.mario.client.bo.BytesType;
import com.mario.client.bo.ColorLightConstants;
import com.mario.client.utils.ColorLightByteUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxz
 * @date 2023年03月07日 10:55
 */
public class ColorLightUpMsgConverter extends ByteSplitService {


    @Override
    protected List<ByteSplit> splitBytes(List<ByteSplit> byteSplits, String content) {
        if (!ColorLightByteUtil.checkSplitRule(byteSplits, content)) {
            //规则解析异常
        }
        return ColorLightByteUtil.doSplitByte(byteSplits, content);
    }


    public void converterMsg(String content) {
        List<ByteSplit> byteSplits;
        if (content.startsWith(ColorLightConstants.SEARCH_DEV_RES)) {
            byteSplits = converterSearchDevRes(content);
        } else if (content.startsWith(ColorLightConstants.DEV_PROPERTY_RES)) {
            byteSplits = converterDevPropertyRes(content);
        }

    }

    /**
     * 探测设备
     */
    private List<ByteSplit> converterSearchDevRes(String content) {
        ArrayList<ByteSplit> byteSplits = new ArrayList<>();
        byteSplits.add(new ByteSplit(6 * bit, bit, "dhcp", "1自动获取，0手动获取", BytesType.TYPE_HEX_TO_DEC));
        byteSplits.add(new ByteSplit(7 * bit, 2 * bit, "port", "端口", BytesType.TYPE_HEX_TO_DEC_LOW));
        byteSplits.add(new ByteSplit(9 * bit, 4 * bit, "ip", "设备ip", BytesType.TYPE_IP));
        byteSplits.add(new ByteSplit(13 * bit, 4 * bit, "mask", "子网掩码", BytesType.TYPE_IP));
        byteSplits.add(new ByteSplit(17 * bit, 4 * bit, "gateway", "网关地址", BytesType.TYPE_IP));
        return splitBytes(byteSplits, content);
    }


    /**
     * 设备属性
     */
    private List<ByteSplit> converterDevPropertyRes(String content) {
        ArrayList<ByteSplit> byteSplits = new ArrayList<>();
        byteSplits.add(new ByteSplit(12 * bit, bit, "sendDevMode", "发送器型号"));
        byteSplits.add(new ByteSplit(13 * bit, bit, "version", "次版本",BytesType.TYPE_HEX_TO_DEC));
        byteSplits.add(new ByteSplit(14 * bit, bit, "majorVersion", "主版本",BytesType.TYPE_HEX_TO_DEC));
        byteSplits.add(new ByteSplit(15 * bit, 4 * bit, "dWord0", "标志位DWORD0"));
        byteSplits.add(new ByteSplit(19 * bit, 4 * bit, "dWord1", "标志位DWORD1"));
        byteSplits.add(new ByteSplit(23 * bit, 4 * bit, "bright", "亮度调整值",BytesType.TYPE_HEX_TO_FLOAT_LOW));
        byteSplits.add(new ByteSplit(39 * bit, 2 * bit, "ct", "色温值",BytesType.TYPE_HEX_TO_DEC_LOW));
        //byteSplits.add(new ByteSplit(56 * bit, 32 * bit, "sn", "设备序列号")); //S6F不支持
        byteSplits.add(new ByteSplit(88 * bit,   bit, "source0", "第0层图像的输入信号源"));
        byteSplits.add(new ByteSplit(90 * bit, 4 * bit, "devTemp", "发送器温度",BytesType.TYPE_HEX_TO_FLOAT_LOW));
        byteSplits.add(new ByteSplit(94 * bit, bit, "netInterNum", "网口数量",BytesType.TYPE_HEX_TO_DEC));
        byteSplits.add(new ByteSplit(95 * bit, bit, "apertureNum", "光口数量",BytesType.TYPE_HEX_TO_DEC));
        byteSplits.add(new ByteSplit(96 * bit, 16 * bit, "netInterState", "网口连接状态"));
        byteSplits.add(new ByteSplit(112 * bit, 8 * bit, "apertureState", "光口连接状态"));
        byteSplits.add(new ByteSplit(120 * bit, 16 * bit, "netInterEnable", "网口输出使能标志"));
        byteSplits.add(new ByteSplit(136 * bit, 8 * bit, "apertureEnable", "光口使能标志"));
        byteSplits.add(new ByteSplit(160 * bit,   bit, "dhcp", "1自动获取，0手动获取",BytesType.TYPE_HEX_TO_DEC));
        byteSplits.add(new ByteSplit(161 * bit, 4 * bit, "ip", "设备ip",BytesType.TYPE_IP));
        byteSplits.add(new ByteSplit(165 * bit, 4 * bit, "mask", "子网掩码",BytesType.TYPE_IP));
        byteSplits.add(new ByteSplit(169 * bit, 4 * bit, "gateway", "网关地址",BytesType.TYPE_IP));
        byteSplits.add(new ByteSplit(173 * bit, 2 * bit, "port", "端口",BytesType.TYPE_HEX_TO_DEC_LOW));
        byteSplits.add(new ByteSplit(175 * bit,  bit, "connect", "网络状态（是否连接）",BytesType.TYPE_HEX_TO_DEC));
        byteSplits.add(new ByteSplit(176 * bit, 4 * bit, "current_ip", "网络状态（当前ip）",BytesType.TYPE_IP));
        byteSplits.add(new ByteSplit(180 * bit, 4 * bit, "current_mask", "网络状态（子网掩码）",BytesType.TYPE_IP));
        byteSplits.add(new ByteSplit(184 * bit, 4 * bit, "current_gateway", "网络状态（网关）",BytesType.TYPE_IP));
        byteSplits.add(new ByteSplit(192 * bit, 21 * bit, "resource_info0", "图层0视频源信息"));
        byteSplits.add(new ByteSplit(213 * bit, 16 * bit, "net_inter_copy_flag", "网口备份标志"));
        byteSplits.add(new ByteSplit(229 * bit,  bit, "UH5_state", "UH5状态",BytesType.TYPE_HEX_TO_DEC_LOW));
        byteSplits.add(new ByteSplit(232 * bit,   bit, "bright_step", "亮度步进值"));
        byteSplits.add(new ByteSplit(233 * bit, bit, "bright_int", "亮度整数值"));
        return splitBytes(byteSplits, content);
    }




}
