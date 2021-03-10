package com.dddbook.bank.messaging;

import com.dddbook.bank.domain.types.AuditMessage;
import com.dddbook.bank.domain.types.SendResult;

public interface AuditMessageProducer {
    SendResult send(AuditMessage message);
}
