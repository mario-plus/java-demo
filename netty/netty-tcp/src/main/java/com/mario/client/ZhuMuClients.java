package com.mario.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author zxz
 * @date 2023年03月09日 14:30
 */

public class ZhuMuClients {
    private static String host = "10.3.52.78";//"10.3.50.195"
    private static Integer port = 16384;
    public static Channel channel;

    public static void main(String[] args) throws InterruptedException {
        String msg = "PROGPLAY 1;";
        connect();
        channel.writeAndFlush(msg);
        Thread.sleep(70 * 1000);
        System.out.println("休眠结束");
        channel.writeAndFlush(msg);
    }


    public static void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("idleStateHandler", new IdleStateHandler(10, 10, 10, TimeUnit.SECONDS));
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });

        ChannelFuture sync = bootstrap.connect(host, port).sync();
        if (sync.isSuccess()) {
            System.out.println("erererer");
            channel = sync.channel();
        }

    }


}
