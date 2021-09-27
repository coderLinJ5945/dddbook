package com.dddbook.bank.domain.service.impl;

import com.dddbook.bank.domain.entity.Account;
import com.dddbook.bank.domain.service.AccountTransferService;
import com.dddbook.bank.external.ExchangeRateService;
import com.dddbook.bank.types.ExchangeRate;
import com.dddbook.bank.types.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 符合领域模型架构的转账服务的业务接口实现类
 */
@Service
public class AccountTransferServiceImpl implements AccountTransferService {

    /**
     * 转账操作
     * @param sourceAccount
     * @param targetAccount
     * @param transferMoney
     * @param exchangeRate
     */
    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money transferMoney, ExchangeRate exchangeRate) {
        Money targetMoney = exchangeRate.exchange(transferMoney);
        sourceAccount.withdraw(transferMoney);
        targetAccount.deposit(targetMoney);
    }
}
