package com.dpw.lyl.join.good.job.iot.web.netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @Author: dengpw
 * @createTime: 2022年08月23日 10:19:45
 * @version: 1.0.0
 * @Description: 解码器
 */
public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        // 定义数组
        final byte[] array;
        // 获取读取长度
        final int length = byteBuf.readableBytes();
        array = new byte[length];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0, length);
        //mp的read方法将其反序列化为object对象
        MessagePack mp = new MessagePack();
        out.add(mp.read(array));
    }
}
