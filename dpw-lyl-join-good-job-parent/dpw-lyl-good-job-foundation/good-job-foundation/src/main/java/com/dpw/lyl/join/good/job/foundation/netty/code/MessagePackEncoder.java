package com.dpw.lyl.join.good.job.foundation.netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @Author: dengpw
 * @createTime: 2022年08月23日 10:15:16
 * @version: 1.0.0
 * @Description: 消息编码器
 */
public class MessagePackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        // messagePack序列化
        MessagePack messagePack = new MessagePack();
       // 采用byteBuf传输
        out.writeBytes(messagePack.write(msg));
    }
}
