package com.mario.server;




import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * 通道初始化
 * 蚂蚁舞
 */
@ChannelHandler.Sharable
public class BootNettyChannelInitializer extends ChannelInitializer<Channel> {

    public static long READ_TIME_OUT = 60;

    public static long WRITE_TIME_OUT = 60;

    public static long ALL_TIME_OUT = 60;

    @Override
    protected void initChannel(Channel ch)  {
//        ch.pipeline().addLast(new IdleStateHandler(READ_TIME_OUT, WRITE_TIME_OUT, ALL_TIME_OUT, TimeUnit.SECONDS));
        ch.pipeline().addLast(new BootNettyChannelInboundHandlerAdapter());

    }

}