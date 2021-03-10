//package com.dddbook.bank.base.event;
//
//import com.dddbook.bank.base.TransactionalUtils;
//import com.dddbook.bank.base.api.MqEvent;
//import com.dddbook.bank.domain.types.AuditMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.MessageChannel;
//
//import javax.validation.constraints.NotNull;
//
//@Slf4j
//public class ApplicationEventImpl implements ApplicationEvent {
//    @Autowired
//    private ApplicationEventBinding applicationEventBinding;
//
//    @Override
//    public void send(MqEvent event) {
//        log.info("stream-producer, event: {}", event);
//        MqUtils.sendToQueue(
//                applicationEventBinding.applicationEventOutput(),
//                event
//        );
//    }
//
//    @Override
//    public void send(MessageChannel channel, MqEvent event) {
//        log.info("stream-producer, event: {}", event);
//        MqUtils.sendToQueue(channel, event);
//    }
//
//    @Override
//    public void sendAfterCommit(MqEvent event) {
//        sendAfterCommit(applicationEventBinding.applicationEventOutput(), event);
//    }
//
//    @Override
//    public void sendAfterCommit(MessageChannel channel, MqEvent event) {
//        log.info("stream-producer, send after commit, event: {}", event);
//        applyAfterCommit(() -> sendToQueue(channel, event));
//    }
//
//    @Override
//    public void applyAfterCommit(@NotNull EventCallback eventCallback) {
//        TransactionalUtils.applyAfterCommit(eventCallback::apply);
//    }
//}
