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

/**
 * 重新按照领域模型架构封装的业务逻辑代码实现类
 */
@Service
@Slf4j
public class TransferServiceImplNew  implements TransferService {

    @Autowired
    //数据存储层(持久化)的业务接口（符合领域模型特性）
    private AccountRepository accountRepository;

    @Autowired
    //用于对接第三方审计消息发送接口（符合领域模型特性）
    private AuditMessageProducer auditMessageProducer;

    @Autowired
    //用于对接第三方获取汇率接口（符合领域模型特性）
    private ExchangeRateService exchangeRateService;


    @Autowired
    //用于转账业务的接口（符合领域模型特性）
    private AccountTransferService accountTransferService;
    @Autowired
    private TransactionalService transactionalService;
//    @Autowired
//    private ApplicationEvent applicationEvent;

    public ResponseEntity<Boolean> transfer(Long sourceUserId, String targetAccountNumber, BigDecimal targetAmount, Integer targetCurrencyId) throws Exception {
        //1. 构造时即可参数校验
        Money targetMoney = new Money(targetAmount, Currency.getById(targetCurrencyId));

        //2. 胶水代码：校验参数同时从数据库读取数据
        /**********
        事务脚本（Transaction Script）代码实现对比：
        AccountDO sourceAccountDO = accountDAO.selectByUserId(sourceUserId);
        AccountDO targetAccountDO = accountDAO.selectByAccountNumber(targetAccountNumber);

        if (!targetAccountDO.getCurrency().equals(targetCurrency)) {
            throw new InvalidCurrencyException();
        }
         ***********/
        Account sourceAccount = accountRepository.find(new UserId(sourceUserId));
        Account targetAccount = accountRepository.find(new AccountNumber(targetAccountNumber));


        //3. 调用第三方接口获取汇率
        /**********
        事务脚本（Transaction Script）代码实现对比：
        BigDecimal exchangeRate = BigDecimal.ONE;
        if (sourceAccountDO.getCurrency().equals(targetCurrency)) {
            exchangeRate = yahooForex.getExchangeRate(sourceAccountDO.getCurrency(), targetCurrency);
        }
        BigDecimal sourceAmount = targetAmount.divide(exchangeRate, RoundingMode.DOWN);
        if (sourceAccountDO.getAvailable().compareTo(sourceAmount) < 0) {
            throw new InsufficientFundsException();
        }
        if (sourceAccountDO.getDailyLimit().compareTo(sourceAmount) < 0) {
            throw new DailyLimitExceededException();
        }
         ***********/
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(sourceAccount.getCurrency(), targetMoney.getCurrency());

        // 4. 转账业务逻辑：计算新值，并且更新字段
        /**********
        事务脚本（Transaction Script）代码实现对比：
        BigDecimal newSource = sourceAccountDO.getAvailable().subtract(sourceAmount);
        BigDecimal newTarget = targetAccountDO.getAvailable().add(targetAmount);
        sourceAccountDO.setAvailable(newSource);
        targetAccountDO.setAvailable(newTarget);
        accountDAO.update(sourceAccountDO);
        accountDAO.update(targetAccountDO);
         ***********/
        accountTransferService.transfer(sourceAccount, targetAccount, targetMoney, exchangeRate);
        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        log.info("source account info {}", sourceAccount);
        log.info("target account info {}", targetAccount);

        // 5.发送审计消息
        /***********
        事务脚本（Transaction Script）代码实现对比：
        String message = sourceUserId + "," + targetAccountNumber + "," + targetAmount + "," + targetCurrency;
        kafkaTemplate.send(TOPIC_AUDIT_LOG, message);
         ***********/
        AuditMessage message = new AuditMessage(sourceAccount.getUserId(), sourceAccount.getAccountNumber(), targetAccount.getAccountNumber(), targetMoney);
        auditMessageProducer.send(message);
//        applicationEvent.sendAfterCommit(message);

        return new ResponseEntity(true);
    }
}
