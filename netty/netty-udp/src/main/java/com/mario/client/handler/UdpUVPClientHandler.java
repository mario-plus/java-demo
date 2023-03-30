package com.mario.client.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mario.client.bo.ByteSplit;
import com.mario.client.bo.FunctionKey;
import com.mario.client.bo.FunctionKeyParams;
import com.mario.client.bo.UvpConstants;
import com.mario.client.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @author zxz
 * @date 2023年03月02日 15:56
 */
public class UdpUVPClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    static UvpMsgService uvpMsgService;

    static {
        uvpMsgService = new UvpMsgService();
    }


    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) {
        int port = msg.sender().getPort();
        System.out.println("端口：" + port);
        ByteBuf content = msg.content();
        byte[] bytes = new byte[content.readableBytes()];
        content.readBytes(bytes);
        if (bytes.length == 640) {
            parseBytes(bytes);
        } else {
            List<ByteSplit> byteSplits = uvpMsgService.splitBytes(
                    FunctionKeyParams.getUpParamFormat(FunctionKey.getScreenRect),
                    new String(bytes, StandardCharsets.UTF_8));
            byteSplits.forEach(System.out::println);
        }


    }


    private String parseBytes(byte[] bytes) {
        List<JSONObject> data = new ArrayList<>();
        if (bytes.length == 640) {//1.71 上行无ack，直接640个字节
            for (int i = 0; i < 64; i++) {
                byte[] bytePlace = Arrays.copyOfRange(bytes, 10 * i, 10 * (i + 1));
                if (checkBytes(bytePlace)) {
                    JSONObject jsonObject = doParseBytes(bytePlace);
                    if (jsonObject != null) {
                        data.add(jsonObject);
                    }
                }
            }
        }
        return JSON.toJSONString(data);

    }


    private JSONObject doParseBytes(byte[] res) {
        for (int i = 0; i < res.length; i++) {
            System.out.print(String.valueOf(res[i]));
        }
        if ("0".equals(String.valueOf(res[0])) ||
                "0".equals(String.valueOf(res[4]))) {
            return null;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", Integer.parseInt(String.valueOf(res[0])));
        jsonObject.put("time", LocalTime.of(Integer.parseInt(String.valueOf(res[1])),
                Integer.parseInt(String.valueOf(res[2])),
                Integer.parseInt(String.valueOf(res[3]))));
        jsonObject.put("sid", Integer.parseInt(String.valueOf(res[4])));
        jsonObject.put("uid", Integer.parseInt(String.valueOf(res[6])));
        jsonObject.put("status", Integer.parseInt(String.valueOf(res[7])));
        return jsonObject;
    }

    private boolean checkBytes(byte[] res) {
        int count = 0;
        for (byte re : res) {
            count = count + Integer.parseInt(String.valueOf(re));
        }
        return count == 1;
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
