package com.mario.server.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * @author zxz
 * @date 2023年03月02日 15:54
 */
public class UdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        String msg = packet.content().toString(CharsetUtil.UTF_8);
        System.err.println("服务端接收消息:" + msg);
        if (msg.startsWith("1")) {
            ctx.write(new DatagramPacket(
                    Unpooled.copiedBuffer("QOTM:111111", CharsetUtil.UTF_8), packet.sender()));
        } else {
            ctx.write(new DatagramPacket(
                    Unpooled.copiedBuffer("QOTM:2222222222", CharsetUtil.UTF_8), packet.sender()));
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        // We don't close the channel because we can keep serving requests.
    }
}