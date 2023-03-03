package com.mario.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * @author zxz
 * @date 2023年03月02日 15:56
 */
public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {


        ByteBuf content = msg.content();
        byte[] bytes = new byte[content.readableBytes()];
        content.readBytes(bytes);
        System.err.println("客户端接收到消息:" + byte2HexWithBlank(bytes));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    public static String byte2HexWithBlank(byte[] b)
    {
        if ((b == null) || (b.length == 0)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(b.length * 3);
        int size = b.length;
        for (int n = 0; n < size; n++) {
            sb.append("0123456789ABCDEF".charAt(0xF & b[n] >> 4)).append("0123456789ABCDEF".charAt(b[n] & 0xF)).append(" ");
        }
        return sb.toString();
    }
}
