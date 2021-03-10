package com.dddbook.bank.mq.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "qevents")
    public void receive(String payload) {
        log.info(">>> 收到审计消息");
        log.info("received payload='{}'", payload);
        latch.countDown();
    }
}
