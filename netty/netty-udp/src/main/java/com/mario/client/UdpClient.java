package com.mario.client;

import com.mario.client.handler.UdpClientHandler;
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

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * @author zxz
 * @date 2023年03月02日 15:53
 */
public class UdpClient {

    static final int PORT = Integer.parseInt(System.getProperty("port", "9099"));
    static Channel channel;

    public static void main(String[] args) throws Exception {
        new Thread(()->{
            try {
                send(11,"EB0000090155EECC00");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                send(12,"EB0000090155EECC00");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                send(13,"EB0000090155EECC00");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }




    private static void send(Integer port,String hex) throws InterruptedException {
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
                    .handler(new UdpClientHandler());
            channel = b.bind(port).sync().channel();
            doSendMsg(hex);
            channel.closeFuture().sync();//阻塞
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void doSendMsg(String hex) throws InterruptedException {
        ByteBuf buf = Unpooled.wrappedBuffer(Hex2Bytes(hex));//"EB0000090155EECC00"
        channel.writeAndFlush(new DatagramPacket(
                buf,
                SocketUtils.socketAddress("10.3.52.243", 0))).sync();//端口为0，自动分配闲置端口
    }


    public static byte[] Hex2Bytes(String hexString) {
        byte[] arrB = hexString.getBytes();
        int iLen = arrB.length;
        byte[] arrOut = new byte[iLen / 2];
        String strTmp = null;
        for (int i = 0; i < iLen; i += 2) {
            strTmp = new String(arrB, i, 2);
            arrOut[(i / 2)] = ((byte) Integer.parseInt(strTmp, 16));
        }
        return arrOut;
    }


}
