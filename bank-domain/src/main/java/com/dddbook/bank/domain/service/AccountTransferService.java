package com.dddbook.bank.domain.service;

import com.dddbook.bank.domain.entity.Account;
import com.dddbook.bank.types.ExchangeRate;
import com.dddbook.bank.types.Money;

public interface AccountTransferService {
    void transfer(Account sourceAccount, Account targetAccount, Money transferMoney, ExchangeRate exchangeRate);
}
