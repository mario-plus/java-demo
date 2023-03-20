package com.mario.client;

import com.mario.client.handler.UdpClientHandler;
import com.mario.client.utils.ByteUtil;
import com.mario.client.utils.ColorLightByteUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.internal.SocketUtils;


/**
 * @author zxz
 * @date 2023年03月02日 15:53
 */
public class UdpClient {
    static final Integer bit = 2;

    static Channel channel;


    public static void main(String[] args) throws Exception {


        new Thread(() -> {

            try {
                sendRandomPort("010011000000FFFFff0000000000010016");
               // sendRandomPort(getDevBrightContent(0, (float) 0.5));
//
              //  sendRandomPort(getDevCTContent(0, 5000));

              //  sendRandomPort(getPropertyContent(0));

//                sendRandomPort(getDevSearchContent());
//
//               sendRandomPort(getDevStopOrStart(0, 0));
//
//                sendRandomPort(getSaveContent(0));
//
//                sendRandomPort(getEnableContent(0, true));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public static String getDevSearchContent() {
        return "EB0000090155EECC00";
    }

    /**
     * 信号源使能
     *
     * @param enable true:允许输入，false:禁止输入
     */
    public static String getEnableContent(Integer devNum, boolean enable) {
        //信号源使能
        String res = "1100110000000000ff0000000000000000";//（1显示，0不显示）
        String content = replaceDev(devNum, res);
        content = replaceRequireRes(content);
        return ByteUtil.replaceChar(content, content.length() - bit, ByteUtil.decToHexLow(enable ? 1 : 0, bit));
    }


    /**
     * 保存配置
     *
     * @param devNum 设备编号，等于0时，广播所有设备
     */
    public static String getSaveContent(Integer devNum) {
        String save = "1000110000000000ff0000000000000001";//保存设置，ff前两位，表示设备序号
        save = replaceRequireRes(save);
        return replaceDev(devNum, save);
    }


    /**
     * 锁屏、解锁
     *
     * @param devNum 设备编号，等于0时，广播所有设备
     * @param state  0不冻结，1冻结
     */
    public static String getDevStopOrStart(Integer devNum, Integer state) {
        //锁屏，解锁
        //                    1200110000000000ff0000000000000000
        String startOrStop = "1200110000000000ff0000000000000000";//ff前两位表示设备序列号，后一位表示1冻结，0不冻结
        String content = replaceDev(devNum, startOrStop);
        content = replaceRequireRes(content);
        return ByteUtil.replaceChar(content, content.length() - bit, ByteUtil.decToHexLow(state, bit));
    }


    /**
     * @param devNum 设备编号，等于0时，广播所有设备
     */
    public static String getPropertyContent(Integer devNum) {
        //查询设备属性
        String getDevProperty = "0100110000000000ff0000000000010016";//查询设备属性，ff前两位，表示设备序号
        getDevProperty = replaceRequireRes(getDevProperty);
        return replaceDev(devNum, getDevProperty);
    }


    /**
     * @param devNum      设备编号，等于0时，广播所有设备
     * @param brightRadio 0%-100%
     */
    public static String getDevBrightContent(Integer devNum, float brightRadio) {//百分比亮度
        //设置亮度
        String setDevBright = "2100140000000000ff000000000000000000003f";//ff前两位表示设备序号，后四位表示亮度值（0-100）
        String content = replaceDev(devNum, setDevBright);
        content = replaceRequireRes(content);
        return ByteUtil.replaceChar(content, content.length() - 4 * bit, ByteUtil.floatTo4BytesLow(brightRadio));
    }


    /**
     * @param devNum 设备编号，等于0时，广播所有设备
     * @param ct     色温（2000-10000）
     */
    public static String getDevCTContent(Integer devNum, int ct) {//设置设备色温2000-10000
        //设置色温
        String setDevCT = "2200120000000000ff000000000000008813";//设置色温，ff前两位，表示设备序号，后两位表示色温1388==>5000（范围2000-10000）
        String content = replaceDev(devNum, setDevCT);
        content = replaceRequireRes(content);
        return ByteUtil.replaceChar(content, content.length() - 2 * bit, ByteUtil.decToHexLow(ct, 2 * bit));
    }


    public static String replaceDev(Integer devNum, String content) {
        return ByteUtil.replaceChar(content, 12, ColorLightByteUtil.allocateDev(devNum));
    }

    /**
     * 指定应答
     * ef 00 04 00
     */
    public static String replaceRequireRes(String content) {
        return ByteUtil.replaceChar(content, 18, "04");
    }


    private static void sendRandomPort(String hex) throws InterruptedException {
        send(0, hex);
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

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.closeFuture().sync();//阻塞
            group.shutdownGracefully();

        }
    }

    public static void doSendMsg(String hex) throws InterruptedException {
        System.out.println("下行指令：" + hex);
        ByteBuf buf = Unpooled.wrappedBuffer(ByteUtil.Hex2Bytes(hex));
        ChannelFuture future = channel.writeAndFlush(new DatagramPacket(
                buf,
                SocketUtils.socketAddress("10.3.50.249", 9099))).sync();//端口为0，自动分配闲置端口
        future.addListener((ChannelFutureListener) channelFuture -> {
            Throwable cause = channelFuture.cause();

            System.out.println(cause);
        });

    }


}
