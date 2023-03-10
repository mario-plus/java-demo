package com.mario.client.handler;

import com.mario.client.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * @author zxz
 * @date 2023年03月02日 15:56
 */
public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    static ColorLightUpMsgService colorLightUpMsgService;



    static {
        colorLightUpMsgService = new ColorLightUpMsgService();
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        ByteBuf content = msg.content();
        byte[] bytes = new byte[content.readableBytes()];
        content.readBytes(bytes);
        String s = ByteUtil.byte2HexWithBlank(bytes).replace(" ","");
        System.out.println("客户端接收到消息:" + s);
        colorLightUpMsgService.converterMsg(ByteUtil.byte2HexWithBlank(bytes).replace(" ",""));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }



}
