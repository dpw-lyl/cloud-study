package com.dpw.lyl.join.good.job.task.service.mq;

import lombok.AllArgsConstructor;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @author Administrator
 */
@Component
@AllArgsConstructor
public class MqCommonManager {
    private final StreamBridge streamBridge;
    private final FunctionCatalog functionCatalog;


    /**
     * 发送消息到指定的输出通道。
     *
     * @param destination 输出通道的名称，例如 kafkaOutput、rabbitOutput、rocketOutput 等。
     * @param payload     要发送的消息内容。
     */
    public void sendMessage(String destination, Object payload) {
        streamBridge.send(destination, MessageBuilder.withPayload(payload).build());
    }



    /**
     * 使用函数绑定接收消息。
     *
     * @param destination 输入通道的名称。
     * @param payloadType 消息载荷的类型。
     * @param <T>         泛型参数类型。
     * @return 函数绑定，可以用于处理消息流。
     */
    public <T> Function<Flux<Message<T>>, Flux<T>> receiveMessagesFunction(String destination, Class<T> payloadType) {
        return functionCatalog.lookup(destination);
    }
}