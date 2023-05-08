package com.mario.server;

/**
 * @author zxz
 * @date 2023年04月23日 11:30
 */



import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *  蚂蚁舞
 */
public class BootNettyServer {

    public static void main(String[] args) throws Exception {
        BootNettyServer bootNettyServer = new BootNettyServer();
        bootNettyServer.bind(1887);
    }

    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap = serverBootstrap.group(bossGroup, workerGroup);
            serverBootstrap = serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap = serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            serverBootstrap = serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            serverBootstrap = serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap = serverBootstrap.childHandler(new BootNettyChannelInitializer());
            ChannelFuture f = serverBootstrap.bind(port).sync();
            if(f.isSuccess()){
                f.channel().closeFuture().sync();
            }
        } catch (InterruptedException e) {
            System.out.println(e.toString());
        } finally {
            bossGroup.shutdownGracefully().sync();
            workerGroup.shutdownGracefully().sync();
        }

    }
}
