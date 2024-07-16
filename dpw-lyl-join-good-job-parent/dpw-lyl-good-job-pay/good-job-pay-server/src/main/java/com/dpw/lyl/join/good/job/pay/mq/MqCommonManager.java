package com.dpw.lyl.join.good.job.pay.mq;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class MqCommonManager {

    private final StreamBridge streamBridge;

    private final StreamMessageHandler streamMessageHandler;
    /**
     * 发送消息到指定的输出通道。
     *
     * @param destination 输出通道的名称，例如 kafkaOutput、rabbitOutput、rocketOutput 等。
     * @param message     要发送的消息内容。
     */
    public void sendMessage(String destination, String message) {
        streamBridge.send(destination, message);
    }

    /**
     * 从指定的输入通道接收消息。
     *
     * @param destination 输入通道的名称，例如 kafkaInput、rabbitInput、rocketInput 等。
     * @return 返回接收到的消息。
     */
    public Flux<String> receiveMessages(String destination) {
        return Flux.from(this.streamMessageHandler.receive(destination))
                .map(message -> (String) message.getPayload());
    }
}