package com.dddbook.bank.application.impl;

import com.dddbook.bank.application.TransferService;
import com.dddbook.bank.application.result.ResponseEntity;
import com.dddbook.bank.base.TransactionalService;
import com.dddbook.bank.base.event.ApplicationEvent;
import com.dddbook.bank.domain.entity.Account;
import com.dddbook.bank.domain.service.AccountTransferService;
import com.dddbook.bank.domain.types.AuditMessage;
import com.dddbook.bank.external.ExchangeRateService;
import com.dddbook.bank.messaging.AuditMessageProducer;
import com.dddbook.bank.repository.AccountRepository;
import com.dddbook.bank.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class TransferServiceImplNew  implements TransferService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuditMessageProducer auditMessageProducer;
    @Autowired
    private ExchangeRateService exchangeRateService;
    @Autowired
    private AccountTransferService accountTransferService;
    @Autowired
    private TransactionalService transactionalService;
//    @Autowired
//    private ApplicationEvent applicationEvent;

    public ResponseEntity<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, Integer targetCurrencyId) throws Exception {
        // 参数校验
        Money targetMoney = new Money(targetAmount, Currency.getById(targetCurrencyId));

        // 读数据
        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

//        transactionalService.transaction(()->{
            // 业务逻辑
            accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);
            // 保存数据
            accountRepository.save(sourceAccount);
            accountRepository.save(targetAccount);
//        });
        log.info("source account info {}", sourceAccount);
        log.info("target account info {}", targetAccount);

        // 发送审计消息
        AuditMessage message = new AuditMessage(sourceAccount.getUserId(), sourceAccount.getAccountNumber(), targetAccount.getAccountNumber(), targetMoney);
        auditMessageProducer.send(message);
//        applicationEvent.sendAfterCommit(message);

        return new ResponseEntity(true);
    }
}
