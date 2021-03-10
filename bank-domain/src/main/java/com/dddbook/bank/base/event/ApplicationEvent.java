package com.dddbook.bank.base.event;

import com.dddbook.bank.base.api.MqEvent;
import com.dddbook.bank.domain.types.AuditMessage;
import org.springframework.messaging.MessageChannel;

import javax.validation.constraints.NotNull;

public interface ApplicationEvent {
    void send(MqEvent event);

    void send(MessageChannel channel, MqEvent event);

    void sendAfterCommit(MqEvent event);

    void sendAfterCommit(AuditMessage event);

    void sendAfterCommit(MessageChannel channel, MqEvent event);

    @FunctionalInterface
    interface EventCallback {
        void apply();
    }

    void applyAfterCommit(@NotNull EventCallback eventCallback);
}
