package com.dddbook.bank.domain.entity;

import com.dddbook.bank.exception.DailyLimitExceededException;
import com.dddbook.bank.exception.InsufficientFundsException;
import com.dddbook.bank.exception.InvalidCurrencyException;
import com.dddbook.bank.types.*;

/**
 * 一个实体（Entity）是拥有ID的域对象，除了拥有数据之外，同时拥有行为。
 * Entity和数据库储存格式无关，在设计中要以该领域的通用严谨语言（Ubiquitous Language）为依据。
 *
 * Entity实体类：Account 是基于领域逻辑的实体类，它的字段和数据库储存不需要有必然的联系。
 * Entity包含数据，同时也应该包含行为。在 Account 里，字段也不仅仅是String等基础类型，
 * 而应该尽可能用上一讲的 Domain Primitive 代替，可以避免大量的校验代码。
 *
 * Data Object数据类：AccountDO是单纯的和数据库表的映射关系，每个字段对应数据库表的一个column，这种对象叫Data Object。
 * DO只有数据，没有行为。AccountDO的作用是对数据库做快速映射，避免直接在代码里写SQL。
 * 无论你用的是MyBatis还是Hibernate这种ORM，从数据库来的都应该先直接映射到DO上，但是代码里应该完全避免直接操作 DO。
 *
 */

public class Account {

    private Integer version;
    private AccountId id;
    private AccountNumber accountNumber;
    private UserId userId;
    private Money available;
    private Money dailyLimit;//每日限额

    public Account() {
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

    public Currency getCurrency() {
        return this.available.getCurrency();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    // 转出
    public void withdraw(Money money) {
        if (this.available.lessThen(money)) {
            throw new InsufficientFundsException("可用金额不足");
        }
        if (this.dailyLimit.lessThen(money)) {
            throw new DailyLimitExceededException("转账金额超过当日操作上限");
        }
        this.available = this.available.subtract(money);
    }

    // 转入
    public void deposit(Money money) {
        if (!this.getCurrency().equals(money.getCurrency())) {
            throw new InvalidCurrencyException("币种不匹配");
        }
        this.available = this.available.add(money);
    }

    @Override
    public String toString() {
        return "Account{" +
                "version=" + version +
                ", id=" + id +
                ", accountNumber=" + accountNumber +
                ", userId=" + userId +
                ", available=" + available +
                ", dailyLimit=" + dailyLimit +
                '}';
    }
}
