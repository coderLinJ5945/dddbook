package com.dddbook.bank.base.event;

import com.dddbook.bank.base.api.MqEvent;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import static java.util.Objects.isNull;

public final class MqUtils {
    public static boolean sendToQueue(MessageChannel channel, Object obj) {
        return channel.send(
                MessageBuilder.withPayload(obj).build()
        );
    }

    public static boolean sendToQueue(MessageChannel channel, MqEvent obj) {
        if (isNull(obj)) {
            return false;
        }
        return channel.send(
                MessageBuilder
                        .withPayload(obj)
                        .setHeader(obj.getRouteKeyHeader(), obj.getRouterKey())
                        .setHeader(obj.getRouterTypeHeader(), obj.getRouteType())
                        .build()
        );
    }
}
