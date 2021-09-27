package com.dddbook.bank.messaging;

import com.dddbook.bank.domain.types.AuditMessage;
import com.dddbook.bank.domain.types.SendResult;

/**
 * 用于对接第三方审计消息发送接口
 */
public interface AuditMessageProducer {
    SendResult send(AuditMessage message);
}
