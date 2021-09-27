package com.dddbook.bank.mq.impl;

import com.dddbook.bank.domain.types.AuditMessage;
import com.dddbook.bank.domain.types.SendResult;
import com.dddbook.bank.messaging.AuditMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
/**
 * kafka消息实现
 */
public class AuditMessageProducerImpl implements AuditMessageProducer {

    private static final String TOPIC_AUDIT_LOG = "TOPIC_AUDIT_LOG";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public SendResult send(AuditMessage message) {
        log.info("开始发送消息{}", message.serialize());
        String messageBody = message.serialize();
        kafkaTemplate.send(TOPIC_AUDIT_LOG, messageBody);
        return new SendResult("发送成功");
    }
}
