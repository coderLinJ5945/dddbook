package com.dddbook.bank.domain.service.impl;

import com.dddbook.bank.domain.entity.Account;
import com.dddbook.bank.domain.service.AccountTransferService;
import com.dddbook.bank.external.ExchangeRateService;
import com.dddbook.bank.types.ExchangeRate;
import com.dddbook.bank.types.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTransferServiceImpl implements AccountTransferService {

    @Override
    public void transfer(Account sourceAccount, Account targetAccount, Money transferMoney, ExchangeRate exchangeRate) {
        Money targetMoney = exchangeRate.exchange(transferMoney);
        sourceAccount.withdraw(transferMoney);
        targetAccount.deposit(targetMoney);
    }
}
