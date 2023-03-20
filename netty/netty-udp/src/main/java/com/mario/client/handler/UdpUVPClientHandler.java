package com.mario.client.handler;

import com.mario.client.bo.ByteSplit;
import com.mario.client.bo.FunctionKey;
import com.mario.client.bo.FunctionKeyParams;
import com.mario.client.bo.UvpConstants;
import com.mario.client.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
        System.out.println("端口："+port);
        ByteBuf content = msg.content();
        byte[] bytes = new byte[content.readableBytes()];
        content.readBytes(bytes);
        List<ByteSplit> byteSplits = uvpMsgService.splitBytes(
                FunctionKeyParams.getUpParamFormat(FunctionKey.getScreenRect),
                new String(bytes));
        byteSplits.forEach(System.out::println);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
