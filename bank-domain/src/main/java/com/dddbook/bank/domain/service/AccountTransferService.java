package com.dddbook.bank.domain.service;

import com.dddbook.bank.domain.entity.Account;
import com.dddbook.bank.types.ExchangeRate;
import com.dddbook.bank.types.Money;

/**
 * 符合领域模型架构的转账服务的业务接口
 */
public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money transferMoney, ExchangeRate exchangeRate);
}
