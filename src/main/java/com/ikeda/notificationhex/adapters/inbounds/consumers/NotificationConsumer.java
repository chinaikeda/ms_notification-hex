package com.ikeda.notificationhex.adapters.inbounds.consumers;

import com.ikeda.notificationhex.adapters.dtos.NotificationRecordComandDto;
import com.ikeda.notificationhex.core.domain.NotificationDomain;
import com.ikeda.notificationhex.core.ports.NotificationServicePort;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    final NotificationServicePort notificationServicePort;

    public NotificationConsumer(NotificationServicePort notificationServicePort) {
        this.notificationServicePort = notificationServicePort;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${ikeda.broker.queue.notificationCommandQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${ikeda.broker.exchange.notificationCommandExchange}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${ikeda.broker.key.notificationCommandKey}")
    )
    public void listen(@Payload NotificationRecordComandDto notificationRecordComandDto){
        var notificationDomain = new NotificationDomain();
        BeanUtils.copyProperties(notificationRecordComandDto, notificationDomain);
        notificationServicePort.saveNotification(notificationDomain);
    }
}
