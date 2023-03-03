package com.mario.server;

import com.mario.server.handler.UdpServerHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author zxz
 * @date 2023年03月02日 15:53
 */
public class UdpServer {


    private static final int PORT = Integer.parseInt(System.getProperty("port", "9099"));

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    // 设置读缓冲区为 10M
                    .option(ChannelOption.SO_RCVBUF, 1024 * 1024*10)
                    // 设置写缓冲区为1M
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)
                    //解决最大接收2048个字节
                    .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(65535))
                    .handler(new UdpServerHandler());
            Channel channel = b.bind(PORT).sync().channel();
            System.out.println(channel.localAddress());
            channel.closeFuture().await();

        } finally {
            group.shutdownGracefully();
        }
    }

}
