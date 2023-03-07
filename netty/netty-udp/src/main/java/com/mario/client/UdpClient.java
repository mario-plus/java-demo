package com.mario.client;

import com.mario.client.handler.UdpClientHandler;
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
public class UdpClient {

    static final int PORT = Integer.parseInt(System.getProperty("port", "9099"));
    static Channel channel;

    static String setDevCT = "2200120000000000ff000000000000008813";//设置色温，ff前两位，表示设备序号，后两位表示色温1388==>5000（范围2000-10000）

    static String save = "1000110000000000ff0000000000000001";//保存设置，ff前两位，表示设备序号

    static String getDevProperty = "0100110000000000ff0000000000010016";//查询设备属性

    static String setDevBright = "2100140000000000ff000000000000000000003f";//ff前两位表示设备序号，后四位表示亮度值（0-100）


    public static void main(String[] args) throws Exception {

        //setDevBright((float) 0.7); //百分比40%,0%息屏

        //setDevCT(6666);

        getDevProperty();
    }

    public static void searchDev() {
        new Thread(() -> {  //探测设备
            try {
                send(0, "EB0000090155EECC00");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void getDevProperty() {
        new Thread(() -> {    //设备属性
            try {
                send(0, getDevProperty);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void setDevCT(int ct) {//设置设备色温2000-10000
        new Thread(() -> {
            try {
                String s1 = setDevCT.substring(0, setDevCT.length() - 4) + ByteUtil.decToByteLow(ct);
                send(0, s1);
                System.out.println("设置设备色温：" + s1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void setDevBright(float num) {//百分比亮度
        new Thread(() -> {    //设置设备亮度
            try {
                String s1 = setDevBright.substring(0, setDevBright.length() - 8) + ByteUtil.floatTo4BytesLow(num);
                System.out.println("设置设备亮度：" + s1);
                send(0, s1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private static void send(Integer port, String hex) throws InterruptedException {
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
                SocketUtils.socketAddress("10.3.52.243", 9099))).sync();//端口为0，自动分配闲置端口
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
