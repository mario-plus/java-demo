package com.mario.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author zxz
 * @date 2023年03月09日 14:30
 */

public class ZhuMuClients {
    private static String host = "10.3.52.246";
    private static Integer port = 5000;
    public static Channel channel;

    public static void main(String[] args) throws InterruptedException {
        connect();
        //channel.writeAndFlush(Unpooled.copiedBuffer(readIpAddr().getBytes()));
        channel.writeAndFlush(Unpooled.copiedBuffer(readDataFromScreen().getBytes()));
    }

    public static String readIpAddr() {
        return "/rdIPAddr:d,1;";
    }

    public static String readDataFromScreen(){
        return "/readDatasGetScreen:d,1;";
    }


    public static void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(host, port)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("decoder", new StringDecoder());
                        ch.pipeline().addLast("encoder", new StringEncoder());
                        ch.pipeline().addLast(new NettyClientHandler());
                    }
                });
        channel = bootstrap.connect().sync().channel();
    }


}
