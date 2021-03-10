package com.dddbook.bank.persistence;

import com.dddbook.bank.types.AccountId;
import com.dddbook.bank.types.AccountNumber;
import com.dddbook.bank.types.Money;
import com.dddbook.bank.types.UserId;

/**
 * 数据库表映射类
 */
public class AccountDO {
    private Integer version;
    private AccountId id;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;

    public AccountDO() {
    }

    public AccountDO(Integer version, AccountId id, AccountNumber accountNumber, UserId userId, Money available, Money dailyLimit) {
        this.version = version;
        this.id = id;
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.available = available;
        this.dailyLimit = dailyLimit;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public AccountId getId() {
        return id;
    }

    public void setId(AccountId id) {
        this.id = id;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(AccountNumber accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public Money getAvailable() {
        return available;
    }

    public void setAvailable(Money available) {
        this.available = available;
    }

    public Money getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(Money dailyLimit) {
        this.dailyLimit = dailyLimit;
    }
}
