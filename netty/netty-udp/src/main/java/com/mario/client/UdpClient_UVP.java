package com.mario.client;

import com.alibaba.fastjson.JSONObject;
import com.mario.client.handler.UdpClientHandler;
import com.mario.client.handler.UdpUVPClientHandler;
import com.mario.client.handler.UdpUvp4000CHandler;
import com.mario.client.utils.ByteUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.internal.SocketUtils;


/**
 * @author zxz
 * @date 2023年03月02日 15:53
 */
public class UdpClient_UVP {

    static Channel channel;

    public static void main(String[] args) throws InterruptedException {
        send(16384, "PROGPLAY 1;".getBytes());
    }

    private static void send(Integer port, byte[] bytes) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    // 设置读缓冲区为 10M
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024 * 10)
                    // 设置写缓冲区为1M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)
                    //解决最大接收2048个字节
                    .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                    //.handler(new UdpUVPClientHandler());
                    .handler(new UdpUvp4000CHandler());
            channel = b.bind(port).sync().channel();
            doSendMsg(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.closeFuture().sync();//阻塞
            group.shutdownGracefully();

        }
    }

    public static void doSendMsg(byte[] bytes) throws InterruptedException {
        System.out.println("下行消息" + new String(bytes));
        ByteBuf buf = Unpooled.wrappedBuffer(bytes);
        channel.writeAndFlush(new DatagramPacket(
                buf,
                SocketUtils.socketAddress("10.3.52.78", 16384))).sync();//端口为0，自动分配闲置端口
    }


}
